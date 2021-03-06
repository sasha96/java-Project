import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class MyJsonSerializer implements JsonSerializer {

    @Override
    public String write(Object obj) throws IllegalAccessException {
        if (obj instanceof List) {
            return writeList(obj);
        } else {
            return writeObj(obj);
        }
    }

    private String writeList(Object obj) throws IllegalAccessException {
        List list = (List) obj;
        String result = "[";
        for (Object listItem : list) {
            result = result + writeObj(listItem) + ",";
        }
        if (list.size() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result + "]";
    }

    private String writeObj(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        String result = "{";
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            boolean isNumber = field.get(obj) instanceof Number;
            boolean isBoolean = field.get(obj) instanceof Boolean;
            boolean isString = field.get(obj) instanceof String;
            boolean isList = field.get(obj) instanceof List;
            if (isNumber || isBoolean) {
                result = result + "\"" + field.getName() + "\":" +
                        field.get(obj) + ",";
            } else {
                if (!isNumber && !isBoolean && !isString && !isList) {
                    result = result + "\"" + field.getName() + "\":" + writeObj(field.get(obj)) + ",";
                } else if (isList) {
                    result = result + "\"" + field.getName() + "\":" + writeList(field.get(obj)) + ",";
                } else {
                    result = result + "\"" + field.getName() + "\":\"" +
                            field.get(obj) + "\",";
                }
            }

        }
        result = result.substring(0, result.length() - 1);
        return result + "}";
    }

    @Override
    public Object read(String string, Class clazz) throws Exception {
        if (clazz.getTypeName().contains("List")) {
            return readList(string, clazz);
        } else {
            return readObject(string, clazz);
        }
    }
    @Override
    public Object read(String string, String stringObject) throws Exception {
            return readList(string, stringObject);
    }

    private Object readObject(String string, Class clazz) throws Exception {
        string = string.replaceAll("\"", "");
        int countNull = 0;
        char ch[] = string.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ':') {
                countNull++;
            }
        }
        if (countNull == 1) {
            return null;
        }
        Object objec = clazz.newInstance();
        Field field[] = clazz.getDeclaredFields();
        string += ",";
        for (Field f : field) {
            if (!string.contains(":")) {
                f.setAccessible(false);
            } else {
                f.setAccessible(true);
                if (f.getType() == Integer.TYPE) {
                    f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (f.getType() == Boolean.TYPE) {
                    f.set(objec, Boolean.parseBoolean(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (f.getType() != String.class && f.getType() != List.class) {
                    String s = string.substring(0, string.indexOf("}") + 1);
                    f.set(objec, read(s, Class.forName(String.valueOf(f.getType().getName()))));
                } else if (f.getType() == List.class) {
                    String s = string.substring(string.indexOf("["), string.indexOf("]") + 1);
                    char[] chars = s.toCharArray();
                    int counter = 0;
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] == '[') {
                            counter++;
                        } else if (chars[i] == '{') {
                            counter++;
                        } else if (chars[i] == ']') {
                            counter--;
                        } else if (chars[i] == '}') {
                            counter--;
                        }
                        if (counter == 0) {
                            s = string.substring(string.indexOf('['), string.indexOf(chars[i]) + 1);
                            f.set(objec, readList(s, (Class<?>) (((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0])));
                        }
                    }
                } else {
                    f.set(objec, (string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                }
            }
        }
        return objec;
    }

    private List<String> readList(String string, String stringObject) throws Exception {
        List list = new ArrayList();
        char[] chars2 = string.toCharArray();
        int counter2 = 0;
        for (int i = 0; i <= chars2.length; i++) {
            string = string.replace("[", "");
            if (string.contains("{")) {
                if (chars2[i] == '{') {
                    counter2++;
                } else if (chars2[i] == '}') {
                    counter2--;
                }
                if (counter2 == 0) {
                    String w = string.substring(string.indexOf('{') + 1, string.indexOf('}'));
                    string = string.substring(string.indexOf("}") + 1);
                    list.add(read(w,Class.forName(stringObject)));
                }
            }
        }
        return list;
}
private List<String> readList(String string, Class clazz) throws Exception {
    if (string.contains("{")) {
        List list = new ArrayList();
        int o = 0;
        int counter = 0;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{') {
                counter++;
            } else if (chars[i] == '}') {
                counter--;
                if (counter == 0) {
                    if (chars[i + 1] == ',') {
                        o++;
                        break;
                    }
                }
            }
        }
        for (int r = 0; r <= o; r++) {
            if (string.contains("},")) {
                System.out.println(string);
                list.add(string.substring(string.indexOf("\"") + 1, string.indexOf(",")));
                string = string.substring(string.indexOf(",") + 1);
            } else {
                list.add(read(string, clazz));

                return list;
            }
        }
        return list;
    } else {
        List<String> list = new ArrayList<>();
        int o = 0;
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar == ',')
                o++;
        }
        for (int r = 0; r <= o; r++) {
            string = string.replace("{", "");
            if (string.contains(",")) {
                list.add(string.substring(string.indexOf("\"") + 1, string.indexOf(",")-1));
                string = string.substring(string.indexOf(",") + 1);
            } else {
                list.add(string.substring(string.indexOf(",") + 2, string.length()-2));
                return list;
            }
        }
        return list;
    }
}
}

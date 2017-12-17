import java.lang.reflect.Field;
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

    String writeList(Object obj) throws IllegalAccessException {
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

    String writeObj(Object obj) throws IllegalAccessException {
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
    public Object read(String string, Class clazz, String type) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (clazz.getTypeName().contains("List")) {
            return readList(string, clazz, type);
        } else {
            return readObject(string, clazz, type);
        }
    }

    private Object readObject(String string, Class clazz, String type) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        string = string.replaceAll("\"", "");
        string = string.replaceAll("]", ",");
        string = string.substring(1);
        string = string.substring(0, string.length() - 1);
        Object objec = clazz.newInstance();
        Field field[] = clazz.getDeclaredFields();
        string += ",";
        for (Field f : field) {
            if (!string.contains(":")) {
                f.setAccessible(false);
            } else {
                f.setAccessible(true);
                if (f.getType()==Integer.TYPE) {
                    f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (f.getType()==Boolean.TYPE) {
                    f.set(objec, Boolean.parseBoolean(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (f.getType() != Integer.TYPE && f.getType() != Boolean.TYPE && f.getType() != String.class && f.getType() != List.class) {
                    String s = string.substring(string.indexOf("{"), string.indexOf("}") + 1);
                    f.set(objec, read(s, Class.forName(String.valueOf(f.getType().getName())), ""));
                } else if (f.getType() == List.class) {
                    String s = string.substring(string.indexOf("[") + 1);
                    s = s.substring(0, s.length() - 1);
                    s += "";
                    f.set(objec, read(s, Class.forName("java.util.ArrayList"), type));
                } else {
                    f.set(objec, (string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                }
            }
        }
        return objec;
    }

    private List<String> readList(String string, Class clazz, String type) {
        string = string.replace("]", "");
        List list = new ArrayList();
        if (type != "") {
            int o = 0;
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ',')
                    o++;
            }
            for (int r = 0; r <= o; r++) {
                string = string.replace("{", "");
                if (string.contains(",")) {

                    list.add(string.substring(string.indexOf("\"") + 1, string.indexOf(",") - 1));
                    string = string.substring(string.indexOf(",") + 1);
                } else {

                    list.add(string.substring(string.indexOf(",") + 2, string.length() - 1));
                    return list;
                }
            }
        } else {
            int o = 0;
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ',')
                    o++;
            }
            for (int r = 0; r <= o; r++) {
                string = string.replace("{", "");
                if (string.contains(",")) {
                    list.add(string.substring(string.indexOf("\"") + 1, string.indexOf(",")));
                    string = string.substring(string.indexOf(",") + 1);
                } else {
                    list.add(string.substring(string.indexOf(",") + 1, string.length()));
                    return list;
                }
            }
        }

        return list;
    }

}


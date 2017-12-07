import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MethodsJson {

    public static String writeJson(Object object1) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        String stringJson = "";
        Class clazz = Class.forName(String.valueOf(object1.getClass().getName()));
        Field field[] = clazz.getDeclaredFields();
        if (clazz.getName().equals("java.util.ArrayList")) {
            List<?> list = (List) object1;
            String s = list.toString();
            if (list.size() == 0) {
                stringJson += "[";
                stringJson += "{}]";
                return stringJson;
            } else if (!s.contains("}")) {
                stringJson += "[";
                String stringOfList = String.valueOf(list);
                if (stringOfList.contains("},")) {
                    for (Object l : list) {
                        stringJson += stringOfList.substring(stringOfList.indexOf("{"), stringOfList.indexOf("}") + 2);
                    }
                } else {
                    for (Object objectList : list) {
                        stringJson += "\"" + objectList + "\"" + ",";
                    }
                }
                stringJson = stringJson.substring(0, stringJson.length() - 1);
                stringJson += "]";
                return stringJson;

            }  else  {
                String per = String.valueOf(list);
                Object var = "";
                stringJson += "[";
                for (Object o : list) {
                    if (per.contains("},")) {
                        var = per.substring(per.indexOf("{") + 1, per.indexOf("},"));
                        per = per.substring(per.indexOf("},") + 1, per.length());
                    } else {
                        var = per.substring(per.indexOf("{") + 1, per.indexOf("}"));
                    }
                    Class cla = Class.forName(String.valueOf(o.getClass().getName()));
                    Field fieldO[] = cla.getDeclaredFields();
                    stringJson += "{";
                    for (Field f : fieldO) {
                        f.setAccessible(true);

                        if (checkString(f.get(o))) {
                            if (f.get(o) instanceof Boolean) {
                                stringJson += "\"" + f.getName() + "\"" + ":" + f.get(o) + ",";
                            } else {
                                stringJson += "\"" + f.getName() + "\"" + ":" + "\"" + f.get(o) + "\"" + ",";
                            }
                        } else {
                            stringJson += "\"" + f.getName() + "\"" + ":" + f.get(o) + ",";
                        }
                    }

                    stringJson = stringJson.substring(0, stringJson.length() - 1);
                    stringJson += "},";
                }
                stringJson = stringJson.substring(0, stringJson.length() - 1);
                stringJson += "]";
            }
        } else {
            stringJson += "{";
            for (Field f : field) {

                f.setAccessible(true);
                clazz.getDeclaredField(f.getName());
                if (checkString(f.get(object1))) {
                    if (f.get(object1) instanceof Boolean) {
                        stringJson += "\"" + f.getName() + "\"" + ":" + f.get(object1) + ",";
                    } else {
                        stringJson += "\"" + f.getName() + "\"" + ":" + "\"" + f.get(object1) + "\"" + ",";
                    }
                } else {
                    stringJson += "\"" + f.getName() + "\"" + ":" + f.get(object1) + ",";
                }
            }
            stringJson = stringJson.substring(0, stringJson.length() - 1);
            stringJson += "}";
        }
        return stringJson;
    }

    public static Object readJson(String string, Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if (clazz.getName().equals("java.util.ArrayList")) {
            List<String> list = new ArrayList();
            string = string.substring(1);
            string = string.replaceAll("\"", "");
            string = string.replaceAll("]", ",");
            Field field[] = clazz.getDeclaredFields();
            for (Field f : field) {
                if (string.contains(",")) {
                    list.add(string.substring(string.indexOf(""), string.indexOf(",")));
                    string = string.substring(string.indexOf(",") + 1);
                }
            }
            return list;
        } else {
            string = string.replaceAll("\"", "");
            string = string.substring(1);
            string = string.substring(0, string.length() - 1);
            Object objec = clazz.newInstance();
            Field field[] = clazz.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                if (!string.contains(":")) {
                    string = "";
                    f.setAccessible(false);
                } else {
                    if (!checkString(string.substring(string.indexOf(":") + 1, string.indexOf(",")))) {
                        f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                        string = string.substring(string.indexOf(",") + 1);
                    } else if (checkString(string.substring(string.indexOf(":") + 1, string.indexOf(",")))) {

                        if (isBoolean(string.substring(string.indexOf(":") + 1, string.indexOf(",")))) {
                            Boolean b = Boolean.valueOf(string.substring(string.indexOf(":") + 1, string.indexOf(",")));
                            f.set(objec, b);
                        } else {
                            f.set(objec, string.substring(string.indexOf(":") + 1, string.indexOf(",")));
                            string = string.substring(string.indexOf(",") + 1);
                            if (!string.contains(",")) {
                                string = string.substring(string.indexOf(":"), string.length()) + ",";
                            }
                        }
                    }
                }

            }
            return objec;
        }
    }

    private static boolean checkString(Object obj) {
        try {
            Integer.parseInt(String.valueOf(obj));
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static boolean isBoolean(String str) {
        try {
            boolean d = Boolean.parseBoolean(str);
            return d;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }
}
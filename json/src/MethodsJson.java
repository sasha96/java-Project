
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodsJson {

    public static String writeJson(Object object1) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        String stringJson = "{";
        Class clazz = Class.forName(String.valueOf(object1.getClass().getName()));
        Field field[] = clazz.getDeclaredFields();
        if (clazz.getName().equals("java.util.ArrayList")) {
            List<?> list = (List) object1;
            for (Object l : list) {
                if (checkString(l)) {
                    stringJson += "\"" + l + "\"" + ",";
                } else {
                    stringJson += l;
                }
            }
            stringJson += "}";
            System.out.println(stringJson);
            return stringJson;

        } else {
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
        System.out.println(stringJson);
        return stringJson;
    }

    public static Object readJson(String string, Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if (clazz.getName().equals("java.util.ArrayList")) {
            List<String> list = new ArrayList();
            for (int i = 0; i < string.length(); i++) {
                string = string.substring(1);
                string = string.replaceAll("\"", "");
                if (!string.contains(",")) {
                    list.add(string.substring(string.indexOf(""), string.indexOf("}")));
                    string = "";
                } else {
                    list.add(string.substring(string.indexOf(":") + 1, string.indexOf(",")));
                    string = string.substring(string.indexOf(","));
                }
            }
            for (Object l : list) {
                System.out.print(l + " ");
            }

            return list;
        } else {
//            Object objec = clazz.newInstance();
//            Class cl = Class.forName(String.valueOf(objec.getClass().getName()));
//            Field field[] = cl.getDeclaredFields();
//            string = string.replaceAll("\"", "");
//            string = string.substring(1);
//            string = string.substring(0, string.length() - 1);
//            System.out.println(string);
//            for (Field f : field) {
//                f.setAccessible(true);
//
//                if (checkString(string.substring(string.indexOf(":") + 1, string.indexOf(",")))) {
//                    f.setInt(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
//                    string = string.substring(string.indexOf(",") + 1);
//
//                }
//                    f.set(objec, String.valueOf(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
//                    string = string.substring(string.indexOf(",") + 1);
//
//
//                System.out.println(objec);
//            }
//            System.out.println(objec);
//            return objec;
            for (int i = 0; i < string.length(); i++) {
                string = string.replaceAll("\"", "");
            }
            string = string.substring(1);
            Object objec = clazz.newInstance();
            Class cl = Class.forName(String.valueOf(objec.getClass().getName()));
            Field field[] = cl.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                if (!string.contains(",")) {
                    string = "";
                    f.setAccessible(false);
                } else {
                    if (!checkString(string.substring(string.indexOf(":") + 1, string.indexOf(",")))) {
                        f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                        string = string.substring(string.indexOf(",") + 1);
                    } else if (checkString(string.substring(string.indexOf(":") + 1, string.indexOf(",")))) {
                        f.set(objec, string.substring(string.indexOf(":") + 1, string.indexOf(",")));
                        string = string.substring(string.indexOf(",") + 1);
                    }else{
                        String t = string.substring(string.indexOf(":") + 1, string.indexOf("}"));
                        System.out.println(t);
                    }
                }
            }
            System.out.println(objec);
            return objec.toString();
        }

    }

    private static boolean checkString(Object obj) {
        if (obj instanceof Number || obj.getClass().isPrimitive()) {
            return false;
        }
        try {
            Integer.parseInt(String.valueOf(obj));

        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
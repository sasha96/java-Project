
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MethodsJson {

    public static String writeJson(Object object, Class clazz){
        String stringJson = "\"{";
        Field field[] = clazz.getDeclaredFields();
        if (clazz.getName().equals("java.util.List")) {
            List list = (List) object;
            for (Object l : list) {
                if (checkString(l)) {
                    stringJson += "\"" + l + "\"" + ",";
                } else {
                    stringJson += l;
                }
            }
            stringJson = stringJson.substring(0, stringJson.length() - 1);
            stringJson += "}\"";
            System.out.println(stringJson);
            return stringJson;
        } else {
            for (Field f : field) {
                f.setAccessible(true);
                try {
                    clazz.getDeclaredField(f.getName());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                try {
                    if (checkString(f.get(object))) {
                        stringJson += "\"" + f.getName() + "\"" + ":" + "\"" + f.get(object) + "\"" + ",";
                    } else {
                        stringJson += "\"" + f.getName() + "\"" + ":" + f.get(object) + ",";
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            stringJson = stringJson.substring(0, stringJson.length() - 1);
            stringJson += "}\"";
        }
        System.out.println(stringJson);
        return stringJson;
    }

    public static Object readJson(String string, Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if (clazz.getName().equals("java.util.List")) {
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
            System.out.println(list);
            System.out.println();
            return list;
        } else {
            for (int i = 0; i < string.length(); i++) {
                string = string.replaceAll("\"", "");
            }
            string = string.substring(1);
            string = string.substring(0, string.length() - 1);
            Object objec = clazz.newInstance();
            Class cl = Class.forName(String.valueOf(objec.getClass().getName()));
            Field field[] = cl.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                if (checkString(string.substring(string.indexOf(":") + 1, string.indexOf(","))) ) {
                    f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (!checkString(string.substring(string.indexOf(":") + 1, string.indexOf(","))) ) {
                    String w = string.substring(string.indexOf(":") + 1, string.indexOf(","));
                    f.set(objec, "" + w + "");
                    string = string.substring(string.indexOf(",") + 1);
                }
                System.out.println(objec);
            }
            System.out.println(objec);
            return objec;
        }
    }

    private static boolean checkString(Object obj) {

        if (obj instanceof Number || obj.getClass().equals(Integer.class)) {
            return false;
        } else {
            return true;
        }
    }
}
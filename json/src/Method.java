import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Method {
    public static String writeJson(List list) {
        String s = "\"{";
        for (Object l : list) {
            if (checkString((String.valueOf(l)))) {
                s += "\"" + l + "\"" + ",";
            } else {
                s += l;
            }
        }
        s = s.substring(0, s.length() - 1);
        s += "}\"";
        System.out.println(s);
        return s;
    }

    public static String writeJson(Object object, Class clazz) throws IllegalAccessException, NoSuchFieldException {
        String s = "\"{";
        Field field[] = clazz.getDeclaredFields();
        for (Field f : field) {
            f.setAccessible(true);
            clazz.getDeclaredField(f.getName());
            if (checkString((String.valueOf(f.get(object))))) {
                s += "\"" + f.getName() + "\"" + ":" + "\"" + f.get(object) + "\"" + ",";
            } else {
                s += "\"" + f.getName() + "\"" + ":" + f.get(object) + ",";
            }
        }
        s = s.substring(0, s.length() - 1);
        s += "}\"";
        System.out.println(s);
        return s;
    }

    public static Object readJson(String string, Class<Object> clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
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
                if (!checkString(string.substring(string.indexOf(":") + 1, string.indexOf(","))) && !string.equals("")) {
                    f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (checkString(string.substring(string.indexOf(":") + 1, string.indexOf(","))) && !string.equals("")) {
                    f.set(objec, string.substring(string.indexOf(":") + 1, string.indexOf(",")));
                    string = string.substring(string.indexOf(",") + 1);
                }
            }
        }
        System.out.println(objec);
        return objec.toString();
    }

    public static List<String> readJson(String string) {
        List<String> list = new ArrayList<String>();
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
        System.out.println();
        return list;
    }

    private static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

}

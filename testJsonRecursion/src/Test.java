import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static String who(Object o)  {
        Class clazz = null;
        try {
            clazz = Class.forName(String.valueOf(o.getClass().getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String result = "";
        if (clazz != ArrayList.class) {
            objectChecing(o);
            result += objectChecing(o);
        } else {
            List list = (ArrayList) o;
            ArrayList arrayList = new ArrayList();
            for (Object object : list) {
                arrayList.add(object);
            }
            result += listChecking(arrayList);
        }
        return result;
    }

    public static String objectChecing(Object o)  {
        Class clazz = null;
        try {
            clazz = Class.forName(String.valueOf(o.getClass().getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field field[] = clazz.getDeclaredFields();
        String stringJson = "";
        stringJson += "{";
        for (Field f1 : field) {
            if (f1.getType().equals(ArrayList.class)) {
                f1.setAccessible(true);
                try {
                    stringJson += listChecking(f1.get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                stringJson += ",";
            } else {
                f1.setAccessible(true);
                try {
                    clazz.getDeclaredField(f1.getName());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                try {
                    if (checkString(f1.get(o))) {
                        if (f1.get(o) instanceof Boolean) {
                            stringJson += "\"" + f1.getName() + "\"" + ":" + f1.get(o) + ",";
                        } else {
                            stringJson += "\"" + f1.getName() + "\"" + ":" + "\"" + f1.get(o) + "\"" + ",";
                        }
                    } else {
                        stringJson += "\"" + f1.getName() + "\"" + ":" + f1.get(o) + ",";
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        stringJson = stringJson.substring(0, stringJson.length() - 1);
        stringJson += "}";
        return stringJson;
    }

    public static String listChecking(Object object1)  {
        String stringJson = "";
        Class clazz = null;
        try {
            clazz = Class.forName(String.valueOf(object1.getClass().getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field field[] = clazz.getDeclaredFields();
        if (clazz.getName().equals("java.util.ArrayList")) {
            List<?> list = (List) object1;
            String s = list.toString();
            if (list.size() == 0) {
                stringJson += "[";
                stringJson += "{}]";
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
            } else {
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
                    Class cla = null;
                    try {
                        cla = Class.forName(String.valueOf(o.getClass().getName()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Field fieldO[] = cla.getDeclaredFields();
                    stringJson += "{";
                    for (Field f : fieldO) {
                        f.setAccessible(true);
                        try {
                            if (checkString(f.get(o))) {
                                if (f.get(o) instanceof Boolean) {
                                    stringJson += "\"" + f.getName() + "\"" + ":" + f.get(o) + ",";
                                } else {
                                    stringJson += "\"" + f.getName() + "\"" + ":" + "\"" + f.get(o) + "\"" + ",";
                                }
                            } else {
                                stringJson += "\"" + f.getName() + "\"" + ":" + f.get(o) + ",";
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    stringJson = stringJson.substring(0, stringJson.length() - 1);
                    stringJson += "},";
                }
                stringJson = stringJson.substring(0, stringJson.length() - 1);
                stringJson += "]";
            }
        }
        return stringJson;
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

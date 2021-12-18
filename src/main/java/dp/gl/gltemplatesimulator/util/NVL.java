package dp.gl.gltemplatesimulator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NVL {
    static Logger logger = LoggerFactory.getLogger(NVL.class);

    public static Integer getInteger(Object src){
        Integer defaultValue=null;
        if(src!=null){
            try{
                defaultValue = (int)Double.parseDouble( src.toString() );
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        return defaultValue;
    }
    public static int getInt(Object src){
        return getInt(src,0);
    }
    public static int getInt(Object src,int defaultValue){
        if(src!=null){
            try{
                //defaultValue = Integer.parseInt( src.toString() );
                defaultValue = (int)Double.parseDouble( src.toString() );
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        return defaultValue;
    }

    public static String getString(Object obj) {
        return getString(obj,"");
    }

    public static String getString(Object obj,String defaultValue) {
        if(obj!=null){
            try{
                defaultValue = obj.toString();
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        return defaultValue;
    }

    public static boolean getBool(Object src){
        return getBool(src,false);
    }
    public static boolean getBool(Object src,boolean defaultValue){
        if(src!=null){
            try{
                //defaultValue = Integer.parseInt( src.toString() );
                String st = src.toString();
                if(st.equalsIgnoreCase("true")
                || st.equalsIgnoreCase("yes")
                || st.equalsIgnoreCase("1")
                || st.equalsIgnoreCase("ok")
                ){
                    defaultValue = true;
                }
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        return defaultValue;
    }


}

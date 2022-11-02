package org.example;

import java.util.Date;

public class time_transform {
    // 示例1

        public static void main(String[] args)
        {
            Date date = new Date();

            System.out.println(date);
            String Str_data=date.toString();
            System.out.println(Str_data.replaceAll("[ :]","_"));
        }

//    public static void main( String[] args ) {
//
//    }
}




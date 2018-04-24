package com.recyclerush.group5.recyclerush;

/**
 * Created by kalayu on 2018-04-22.
 */

public class UserBarcode {
        String number;
        public boolean isRecylable() {
            if(number.equals("7340131610000") || number.equals("7311250004360"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
      }

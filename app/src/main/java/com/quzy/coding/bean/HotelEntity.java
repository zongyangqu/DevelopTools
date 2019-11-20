package com.quzy.coding.bean;

import java.util.ArrayList;


public class HotelEntity {

    public ArrayList<TagsEntity> allTagsList;

    public class TagsEntity {
        public String tagsName;
        public ArrayList<TagInfo> tagInfoList;

        public class TagInfo {
            public String tagName;
        }
    }

}

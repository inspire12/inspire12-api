package com.inspire12.practice.api.domain.house;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement
public class HouseApply {
    public Header Header;
    public Body Body;

    public static class Header {
        public int ResultCode;
        public String ResultMsg;
    }

    public static class Item {
        public String BsnsMbyNm;
        public String HouseDtlSecdNm;
        public int HouseManageNo;
        public String HouseNm;
        public int PblancNo;
        public LocalDate PrzwnerPresnatnDe;
        public LocalDate RceptBgnde;
        public LocalDate RceptEndde;
        public int RcritPblancDe;
        public String RentSecdNm;
        public String Sido;
    }

    public static class Items {
        public List<Item> Item;
    }

    public static class Body {
        public Items Items;
        public int NumOfRows;
        public int PageNo;
        public int TotalCount;
    }
}

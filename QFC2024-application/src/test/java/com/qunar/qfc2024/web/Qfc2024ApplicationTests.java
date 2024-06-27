package com.qunar.qfc2024.web;

import com.qunar.qfc2024.domain.Facade.codelines.CodeLineFacade;
import com.qunar.qfc2024.domain.Facade.loganalysis.AccessFacade;
import com.qunar.qfc2024.domain.Facade.shellsimulation.ShellFacade;
import com.qunar.qfc2024.domain.Facade.textdecryption.TextDecryptFacade;
import com.qunar.qfc2024.domain.bo.GroupedURL;
import com.qunar.qfc2024.domain.bo.InterfaceInfo;
import com.qunar.qfc2024.domain.bo.InterfaceStat;
import com.qunar.qfc2024.domain.bo.MethodStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class Qfc2024ApplicationTests {

    @Autowired
    private AccessFacade accessFacade;

    @Autowired
    private CodeLineFacade codeLineService;

    @Autowired
    private TextDecryptFacade textDecryptService;

    @Autowired
    private ShellFacade shellService;

    @Test
    void test() {

        InterfaceInfo interfaceInfo = new InterfaceInfo("POST /user/postDeviceInfo.htm");
        System.out.println(interfaceInfo);
        //InterfaceInfo(uuid=1bb29872-0bd0-4ba6-9760-c603641a5f56, allQueryString=POST /user/postDeviceInfo.htm, method=POST, url=/user/postDeviceInfo.htm, args=[], argCount=0)

        InterfaceInfo interfaceInfo2 = new InterfaceInfo("GET /twell/public.htm?arg1=var1&arg2=var2");
        System.out.println(interfaceInfo2);
        //InterfaceInfo(uuid=987a3ded-78f3-412d-be62-36175eb26ca2, allQueryString=GET /twell/public.htm?arg1=var1&arg2=var2, method=GET, url=/twell/public.htm, args=[JSP Tag Param: name 'arg1', value 'var1', JSP Tag Param: name 'arg2', value 'var2'], argCount=2)
    }

    @Test
    void getQueryCountTest() {
        Integer count = accessFacade.getQueryCount(null);
        System.out.println(count);
        //132585
    }

    @Test
    void getFrequentInterfaceTest() {
        List<InterfaceStat> frequentInterface = accessFacade.getFrequentInterface(null, 10L);
        System.out.println(frequentInterface);
        //[InterfaceStat(method=null, url=/duty/getToDoDutyCount.json, queryCount=23396), InterfaceStat(method=null, url=/twell/public.htm, queryCount=16192), InterfaceStat(method=null, url=/notification/queryMessageByUid.htm, queryCount=16052), InterfaceStat(method=null, url=/twell/querytwellDetailForMobile.htm, queryCount=9584), InterfaceStat(method=null, url=/geo/coord2city.json, queryCount=6085), InterfaceStat(method=null, url=/twell/private.htm, queryCount=5826), InterfaceStat(method=null, url=/user/getResources.json, queryCount=5585), InterfaceStat(method=null, url=/user/getUserInfo.json, queryCount=5300), InterfaceStat(method=null, url=/location/getOneAuthCity.htm, queryCount=5207), InterfaceStat(method=null, url=/user/postDeviceInfo.htm, queryCount=4878)]
        System.out.println(frequentInterface.size());
    }

    @Test
    void getQueryMethodCountTest() {
        List<MethodStat> list = accessFacade.getQueryMethodCount(null);
        System.out.println(list);
        //[InterfaceStat(method=POST, url=null, queryCount=16008), InterfaceStat(method=GET, url=null, queryCount=116575)]
    }

    @Test
    void getGroupedURLTest() {
        List<GroupedURL> groupedURL = accessFacade.getGroupedURL(null);
        System.out.println(groupedURL);
        //[GroupedURL(category=/visitrecord, urls=[/visitrecord/addVisitRecordNew.htm, /visitrecord/queryVisitRecordForPage.htm, /visitrecord/suggestAccompanyVisitUsers.json, /visitrecord/updateVisitRecordNew.htm]), GroupedURL(category=/org, urls=[/org/queryOrgWithUser.json]), GroupedURL(category=/transferhistory, urls=[/transferhistory/queryTransHis.htm]), GroupedURL(category=/accompanyvisitrecord, urls=[/accompanyvisitrecord/saveAccompanyVisitRecord.json]), GroupedURL(category=/monitor, urls=[/monitor/qmonitor.jsp]), GroupedURL(category=/visitmanage, urls=[/visitmanage/listVisitInfo.htm]), GroupedURL(category=/geo, urls=[/geo/coord2city.json]), GroupedURL(category=/notification, urls=[/notification/queryMessageByUid.htm, /notification/markRead.htm, /notification/queryMessageByUid.json, /notification/queryDetail.htm]), GroupedURL(category=/contact, urls=[/contact/listContactPage.htm, /contact/mylist.htm, /contact/addNew.htm, /contact/updateNew.htm]), GroupedURL(category=/twell, urls=[/twell/querytwellDetailForMobile.htm, /twell/public.htm, /twell/private.htm, /twell/sortingIntwell.htm, /twell/giveUptwells.htm, /twell/allotUserTotwell.json, /twell/queryRoomTypeRoomAmount.json, /twell/addNewtwell1.htm, /twell/editRoomTypeRoomAmount.json, /twell/modifytwellInfo1.htm, /twell/allotUserSuggest.json, /twell/reporttwellInfoError.htm]), GroupedURL(category=/duty, urls=[/duty/getToDoDutyCount.json, /duty/getDutyListNew.json, /duty/detail.json, /duty/processDuty.json, /duty/getDutyListPage.json, /duty/getCategoryTreeFilterEmptyNew.json]), GroupedURL(category=/accompanyvisitmanage, urls=[/accompanyvisitmanage/listAccompanyVisitInfo.json]), GroupedURL(category=/teamchangeprice, urls=[/teamchangeprice/listQueryForMobile.json, /teamchangeprice/editPriceDetailForMobile.json, /teamchangeprice/editPrice.json]), GroupedURL(category=/location, urls=[/location/getOneAuthCity.htm, /location/queryCitiesByParentId.htm]), GroupedURL(category=/tag, urls=[/tag/getSearchTagList.json]), GroupedURL(category=/twelldetail, urls=[/twelldetail/cooperation.htm]), GroupedURL(category=/user, urls=[/user/getResources.json, /user/getUserInfo.json, /user/postDeviceInfo.htm, /user/login.htm, /user/sendVerifyCode.htm, /user/logout.htm]), GroupedURL(category=/_, urls=[/_/jarinfo])]
    }

    @Test
    void getCodeLineCountTest() {
        Long count = codeLineService.getCodeLineCount(null);
        System.out.println(count);
        //2100
    }

    @Test
    void decryptTest() throws IOException {
        textDecryptService.textDecrypt(null, 1, 10);
    }

    @Test
    void shellTest() {
//        String cmd = "cat out\\sdxl.txt";
//        String cmd = "cat out\\sdxl.txt | grep 我 ";
        String cmd = "cat out\\sdxl.txt | grep 我 | wc -l";

//        String cmd = "grep 我 out\\sdxl.txt";

//        String cmd = "wc -l out\\sdxl.txt";
        shellService.run(cmd);
    }
}

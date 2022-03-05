package com.inspire12.practice.api.domain.house;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * https://www.data.go.kr/data/15081187/openapi.do
 *
 */
@Slf4j
@AllArgsConstructor
public class HouseApplyRequest {
    private final String baseUrl = "http://openapi.reb.or.kr/OpenAPI_ToolInstallPackage/service/rest/ApplyhomeInfoSvc/getLttotPblancList";
    private final String serviceKey = "uHxfIUIPwslyveijr1tH5sjhn169wqrQjdpvAPm9uJC9KZcX5pZh%2BSNjIDGJLbnvefaNiomZfbwWpOSjvfJxUQ%3D%3D";
    private String startMonth;//202112
    private String endMonth;
    private String houseSecd; //
    private String sido;
    private int pageNo = 1;
    private int numOfRows = 20;

    public URI createUri() {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?serviceKey=").append(serviceKey);
        if (StringUtils.hasText(startMonth)) {
            urlBuilder.append("&startmonth=").append(startMonth);
        }
        if (StringUtils.hasText(endMonth)) {
            urlBuilder.append("&endmonth=").append(endMonth);
        }
        if (StringUtils.hasText(houseSecd)) {
            urlBuilder.append("&houseSecd=").append(houseSecd);
        }
        if (StringUtils.hasText(sido)) {
            urlBuilder.append("&sido=").append(sido);
        }
        urlBuilder.append("&pageNo=").append(pageNo);
        urlBuilder.append("&numOfRows=").append(numOfRows);

        try {
            return new URI(urlBuilder.toString());
        } catch (URISyntaxException e) {
            log.warn("url request getLttotPblancList: ", e);
        }
        throw new RuntimeException();
    }
}

package org.david.crytocurrency.service;

import com.slack.api.Slack;
import lombok.RequiredArgsConstructor;
import org.david.crytocurrency.http.SlackHttpClient;
import org.david.crytocurrency.http.UpbitHttpClient;
import org.david.crytocurrency.http.UpbitTickerDto;
import org.david.crytocurrency.repository.ReportHistoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpbitSlackService {
    private final SlackHttpClient slackHttpClient;
    private final UpbitHttpClient upbitHttpClient;
    private final ReportHistoryRepository repository;

    public void execute(String market){
        // 업비트
        UpbitTickerDto upbitTickerDto = upbitHttpClient.getTickerByMarket(market);

        // 슬랙
        StringBuilder sb = new StringBuilder();
        sb.append("[실시간 데이터]");
        sb.append("[" + market + "]");
        sb.append("[price]: ");
        sb.append(upbitTickerDto.getTrade_price());
        slackHttpClient.send(sb.toString());

        // 저장
        repository.save(market, String.valueOf(upbitTickerDto.getTrade_price()));
    }



}

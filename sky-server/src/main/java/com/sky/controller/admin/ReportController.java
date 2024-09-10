package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/admin/report")
@Api("数据统计")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额")
    public Result<TurnoverReportVO> turnoverStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        return Result.success(reportService.getTurnoverReport(begin,end));
    }

    @GetMapping("/userStatistics")
    @ApiOperation("用户统计接口")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        return Result.success(reportService.getUserReport(begin,end));
    }

    @GetMapping("/ordersStatistics")
    @ApiOperation("订单统计接口")
    public Result<OrderReportVO> OrderStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        return Result.success(reportService.getOrderReport(begin,end));
    }


    @GetMapping("/top10")
    @ApiOperation("查询销量排名top10接口")
    public Result<SalesTop10ReportVO> SalesTop10Statistics(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        return Result.success(reportService.getSalesTop10Report(begin,end));
    }

    @GetMapping("/export")
    @ApiOperation("导出报表接口")
    public void export(HttpServletResponse response) throws IOException {
        reportService.outexport(response);
    }

}

package com.example.rcstask.controller;

import com.example.rcstask.model.Column;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/")
public class TableController {
    private final JdbcTemplate jdbcTemplate;

    public TableController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Map<String, Object>> calculateTable(@RequestParam("row") Column row,
                                                    @RequestParam("col") Column col) {
        String selectSql = String.format("SELECT %s AS row, %s AS col, SUM(v) AS val FROM source_data", row, col);
        String groupSql = String.format(" GROUP BY %s, %s", row, col);
        return jdbcTemplate.queryForList(selectSql + groupSql);
    }
}

package com.github.siberianintegrationsystems.restApp.controller.dto;

public class JournalRequestDTO {
    public String search;
    public int page;
    public int pageSize;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public JournalRequestDTO() {
    }
}

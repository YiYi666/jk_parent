package top.greathead.jk.service;

import top.greathead.jk.entity.PackingList;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface PackingListService {
    Pagination findByPage(Pagination page);

    List<PackingList> findAll();

    void insert(PackingList model);

    PackingList findById(String id);

    void update(PackingList model);

    void delete(String[] ids);

    void updateState(String id, Long state);

    Pagination findByPage(Pagination page, Long state);
}

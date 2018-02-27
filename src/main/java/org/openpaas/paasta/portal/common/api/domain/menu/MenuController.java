package org.openpaas.paasta.portal.common.api.domain.menu;

import org.openpaas.paasta.portal.common.api.entity.portal.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by indra on 2018-02-23.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 메뉴 최대값을 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getMenuMaxNoList"}, method = RequestMethod.POST)
    public Map<String, Object> getMenuMaxNoList(@RequestBody Menu menu) {
        return menuService.getMenuMaxNoList(menu);
    }

    /**
     * 메뉴를 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getMenuList"}, method = RequestMethod.POST)
    public Map<String, Object> getMenuList(@RequestBody Menu menu) {
        return menuService.getMenuList(menu);
    }

    /**
     * 메뉴를 상세 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getMenuDetail"}, method = RequestMethod.POST)
    public Map<String, Object> getMenuDetail(@RequestBody Menu menu) {
        return menuService.getMenuDetail(menu);
    }

    /**
     * 메뉴를 등록한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/insertMenu"}, method = RequestMethod.POST)
    public Map<String, Object> insertMenu(@RequestBody Menu menu) {
        return menuService.insertMenu(menu);
    }

    /**
     * 메뉴를 수정한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/updateMenu"}, method = RequestMethod.POST)
    public Map<String, Object> updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    /**
     * 메뉴를 삭제한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/deleteMenu"}, method = RequestMethod.POST)
    public Map<String, Object> deleteMenu(@RequestBody Menu menu) {
        return menuService.deleteMenu(menu);
    }

    /**
     * 사용자 메뉴를 조회한다.
     *
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getUserMenuList"}, method = RequestMethod.POST)
    public Map<String, Object> getUserMenuList(@RequestBody Menu menu) {
        return menuService.getUserMenuList(menu);
    }

}

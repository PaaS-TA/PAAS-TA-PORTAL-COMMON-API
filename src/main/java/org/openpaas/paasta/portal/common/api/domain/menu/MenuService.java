package org.openpaas.paasta.portal.common.api.domain.menu;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.Menu;
import org.openpaas.paasta.portal.common.api.repository.portal.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by indra on 2018-02-23.
 */
@Service
public class MenuService {

    @Autowired
    JinqSource jinqSource;

    @Autowired
    MenuRepository menuRepository;

    /**
     * 메뉴 최대값을 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    public HashMap<String, Object> getMenuMaxNoList(Menu menu) {
        HashMap<String, Object> resultMap = new HashMap<>();

        int maxNo = jinqSource.streamAllPortal(Menu.class)
                .max(Menu::getNo);

        resultMap.put("MAX_NO", maxNo);
        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        return resultMap;
    }

    /**
     * 메뉴 목록을 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    public HashMap<String, Object> getMenuList(Menu menu) {
        HashMap<String, Object> resultMap = (HashMap<String, Object>) this.getRecursiveMenuList(menu);

        return new HashMap<String, Object>() {{
            put("RESULT_DATA", new HashMap<String, Object>() {{
                put("id", 0);
                put("text", "ROOT");
                put("item", new ArrayList<Object>() {{
                            add(new HashMap<String, Object>() {{
                                put("id", -1);
                                put("text", "메뉴");
                                put("open", 1);
                                put("item", resultMap.get("item"));
                            }});
                        }}
                );
            }});

            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 메뉴 목록을 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    private Map<String, Object> getRecursiveMenuList(Menu menu) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        HashMap<String, Object> resultMap = new HashMap<>();

        Menu menuRe = new Menu();

        if(null != menu) {
            menuRe.setParentNo(menu.getNo());
        } else {
            menuRe.setParentNo(0);
        }

        int no = menuRe.getNo();
        int parentNo = menuRe.getParentNo();
        String useYn = menuRe.getUseYn();

        JinqStream<Menu> streams = jinqSource.streamAllPortal(Menu.class);

        if(no != 0) {
            streams = streams.where(c -> c.getNo() == no);
        }
        if(null != useYn && !"".equals(useYn)) {
            streams = streams.where(c -> c.getUseYn().equals(useYn));
        }
        streams = streams.where(c -> c.getParentNo() == parentNo);

        streams = streams.sortedBy(c -> c.getNo());
        streams = streams.sortedBy(c -> c.getSortNo());
        streams = streams.sortedBy(c -> c.getParentNo());

//        streams.forEach(
//                c -> System.out.println(c.getParentNo() + " " + c.getSortNo() + " " + c.getNo())
//        );

        List<Menu> menuList = streams.toList();

        for (Menu menuModel : menuList) {
            HashMap<String, Object> menuItemMap = new HashMap<String, Object>() {{
                put("id", menuModel.getNo());
                put("text", menuModel.getMenuName());
            }};

            if (menuModel.getChildCount() > 0) {
                // GET CHILD NODE (RECURSIVE)
                menuItemMap.put("item", this.getRecursiveMenuList(menuModel).get("item"));
            }

            resultList.add(menuItemMap);
        }

        return new HashMap<String, Object>() {{
            put("item", resultList);
        }};
    }

    /**
     * 메뉴 상세를 조회한다.
     *
     * @param menu Menu(모델클래스)
     * @return Map(자바클래스)
     */
    public HashMap<String, Object> getMenuDetail(Menu menu) {
        HashMap<String, Object> resultMap = new HashMap<>();

        int no = menu.getNo();

        JinqStream<Menu> streams = jinqSource.streamAllPortal(Menu.class);
        streams = streams.where(c -> c.getNo() == no);

        resultMap.put("RESULT_DATA", streams.toList());
        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        return resultMap;
    }

    /**
     * 메뉴를 등록한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertMenu(Menu param) {
        HashMap<String, Object> resultMap = new HashMap<>();

        menuRepository.save(param);

        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        return resultMap;
    }

    /**
     * 메뉴를 수정한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateMenu(Menu param) {
        HashMap<String, Object> resultMap = new HashMap<>();

        menuRepository.save(param);

        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        return resultMap;
    }

    /**
     * 메뉴를 삭제한다.
     *
     * @param param Menu(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteMenu(Menu param) {
        HashMap<String, Object> resultMap = new HashMap<>();

        menuRepository.delete(param);

        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        return resultMap;
    }

    /**
     * 사용자 메뉴 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    public Map<String, Object> getUserMenuList(Menu param) {
        HashMap<String, Object> resultMap = new HashMap<>();

        int parentNo = param.getParentNo();
        String useYn = param.getUseYn();

        JinqStream<Menu> streams = jinqSource.streamAllPortal(Menu.class);

        streams = streams.where(c -> c.getParentNo() == 0);
        streams = streams.where(c -> c.getUseYn().equals(Constants.USE_YN_Y));

        streams = streams.sortedBy(c -> c.getNo());
        streams = streams.sortedBy(c -> c.getSortNo());
        streams = streams.sortedBy(c -> c.getParentNo());

        resultMap.put("list", streams.toList());
        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        return resultMap;
    }

}

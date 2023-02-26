package cn.ssm.right.po;

import java.util.List;

public class Menu {
    private Integer id;
    private String name;
    private String url;
    private String ico;
    private Integer status;
    private String parent_id;
    private Menu parent_menu;
    private List<Menu> childMenus;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getIco() {
        return ico;
    }

    public Integer getStatus() {
        return status;
    }

    public String getParent_id() {
        return parent_id;
    }

    public Menu getParent_menu() {
        return parent_menu;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setParent_menu(Menu parent_menu) {
        this.parent_menu = parent_menu;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", ico='" + ico + '\'' +
                ", status=" + status +
                ", parent_id='" + parent_id + '\'' +
                ", parent_menu=" + parent_menu +
                ", childMenus=" + childMenus +
                '}';
    }
}

package com.zh.biggunsbombardjapan.person;

import android.graphics.Point;

public abstract class BaseRole {

    //位置 在棋盘上的位置
    private Point point;
    //本身的宽度
    private int width;
    //身份 一共两种角色：1 大炮  2 小兵
    private int role;

    public BaseRole(Point point, int width, int role) {
        this.point = point;
        this.width = width;
        this.role = role;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getWidth() {
        return width;
    }

    /**
     * 获取身份
     * @return
     */
    public int getRole() {
        return role;
    }


}

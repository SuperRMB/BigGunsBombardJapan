package com.zh.biggunsbombardjapan.battle_forge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zh.biggunsbombardjapan.person.BaseRole;
import com.zh.biggunsbombardjapan.person.RoleFactory;
import com.zh.biggunsbombardjapan.util.Common;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class BattleForgeView extends View {

    private int width;
    private int height;
    private Paint mPaint;
    //棋盘间距
    private int spacing;
    private int mErrorRange;
    private Rect mOutFrame;
    private Rect mInnerFrame;
    private List<Rect> mPointsRange = new ArrayList<>();
    private List<Point> mPoints = new ArrayList<>();
    private Rect mClickRect;
    private RoleFactory mRoleFactory;
    private Paint mRolePaint;

    public BattleForgeView(Context context) {
        super(context);
        init();
    }


    public BattleForgeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public BattleForgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        spacing = Common.dp2px(getContext(), 80);
        mErrorRange = Common.dp2px(getContext(), 15);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mRolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRolePaint.setStyle(Paint.Style.FILL);

        mRoleFactory = new RoleFactory();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int totalWidth = spacing * 4;
        setMeasuredDimension(totalWidth, totalWidth);

        int i = Common.dp2px(getContext(), 4);
        width = height = spacing * 3 + i * 2;
        int outLeft = (totalWidth - width) / 2;
        mOutFrame = new Rect(outLeft, outLeft, width + outLeft, height + outLeft);

        mInnerFrame = new Rect(outLeft + i, outLeft + i, width - i + outLeft, height - i + outLeft);

        mPoints.clear();
        mPointsRange.clear();
        //创建棋盘所有的订单信息
        for (int j = 0; j < 4; j++) {
            int y = outLeft + i + spacing * j;
            for (int k = 0; k < 4; k++) {
                int x = outLeft + i + spacing * k;
                Rect rect = new Rect(x - mErrorRange, y - mErrorRange, x + mErrorRange, y + mErrorRange);
                Point point = new Point(x,y);
                mPoints.add(point);
                mPointsRange.add(rect);
            }
        }

        mRoleFactory.initRole(mPoints);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制棋盘
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(Common.dp2px(getContext(), 3));
        canvas.drawRect(mOutFrame, mPaint);

        mPaint.setStrokeWidth(Common.dp2px(getContext(), 1));
        canvas.drawRect(mInnerFrame, mPaint);

        int startX1 = mInnerFrame.left + spacing;
        canvas.drawLine(startX1, mInnerFrame.top, startX1, mInnerFrame.bottom, mPaint);

        int startX2 = mInnerFrame.left + spacing * 2;
        canvas.drawLine(startX2, mInnerFrame.top, startX2, mInnerFrame.bottom, mPaint);

        int startY1 = mInnerFrame.left + spacing;
        canvas.drawLine(mInnerFrame.left, startY1, mInnerFrame.right, startY1, mPaint);

        int startY2 = mInnerFrame.left + spacing * 2;
        canvas.drawLine(mInnerFrame.left, startY2, mInnerFrame.right, startY2, mPaint);


        //绘制角色
        mRolePaint.setColor(Color.RED);
        List<BaseRole> cannonList = mRoleFactory.getCannonList();
        for (int i = 0; i < cannonList.size(); i++) {
            BaseRole baseRole = cannonList.get(i);
            Point point = baseRole.getPoint();
            canvas.drawCircle(point.x,point.y,baseRole.getWidth(),mRolePaint);
        }

        mRolePaint.setColor(Color.BLUE);
        List<BaseRole> soldierList = mRoleFactory.getSoldierList();
        for (int i = 0; i < soldierList.size(); i++) {
            BaseRole baseRole = soldierList.get(i);
            Point point = baseRole.getPoint();
            canvas.drawCircle(point.x,point.y,baseRole.getWidth(),mRolePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                mClickRect = getPoint(x, y);
                if (mClickRect != null) {
                    invalidate();
                }
                break;
        }

        return super.onTouchEvent(event);
    }


    private Rect getPoint(int x, int y) {
        for (int i = 0; i < mPointsRange.size(); i++) {
            Rect rect = mPointsRange.get(i);
            if (rect.contains(x,y)){
                return rect;
            }
        }
        return null;
    }
}

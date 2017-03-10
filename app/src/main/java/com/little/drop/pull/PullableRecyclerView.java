package com.little.drop.pull;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.little.drop.listener.Pullable;

public class PullableRecyclerView extends RecyclerView implements Pullable
{

	public PullableRecyclerView(Context context)
	{
		super(context);
	}

	public PullableRecyclerView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableRecyclerView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown()
	{
		RecyclerView.LayoutManager layoutManager = getLayoutManager();
		int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
		int count = layoutManager.getItemCount();
		if (count == 0)
		{
			// 没有item的时候也可以下拉刷新
			return true;
		} else if (firstVisibleItemPosition == 0
				&& getChildAt(0).getTop() >= 0)
		{
			// 滑到ListView的顶部了
			return true;
		} else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		RecyclerView.LayoutManager layoutManager = getLayoutManager();
		int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
		int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
		int count = layoutManager.getItemCount();
		if (count == 0)
		{
			// 没有item的时候也可以上拉加载
			return true;
		} else if (lastVisibleItemPosition == (count - 1))
		{
			// 滑到底部了
			if (getChildAt(lastVisibleItemPosition - firstVisibleItemPosition) != null
					&& getChildAt(
					lastVisibleItemPosition - firstVisibleItemPosition).getBottom() <= getMeasuredHeight())
				return true;
		}
		return false;
	}
}

package com.example.erecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emreeran.erecyclerview.ERecyclerView;
import com.emreeran.erecyclerview.loadmore.DefaultLoadMoreFooter;
import com.emreeran.erecyclerview.loadmore.OnLoadMoreListener;
import com.emreeran.erecyclerview.pulltorefresh.OnRefreshListener;
import com.emreeran.erecyclerview.pulltorefresh.TwitterRefreshHeader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_e_recycler_view)
    ERecyclerView mERecyclerView;

    @Bind(R.id.main_quick_return_text_view)
    TextView mQuickReturnHeaderTextView;

    @Bind(R.id.main_quick_return_footer_text_view)
    TextView mQuickReturnFooterTextView;

    private ArrayList<String> mItemList = new ArrayList<>();

    private Adapter mAdapter;

    private int mItemCount = 0;

    private int mLoadTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        for (; mItemCount < 30; mItemCount++) {
            mItemList.add("List item " + mItemCount);
        }

        // Set layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mERecyclerView.setLayoutManager(layoutManager);

        // Add pull to refresh header
        mERecyclerView.setPullToRefreshView(TwitterRefreshHeader.getResource(), TwitterRefreshHeader.getListener());
        mERecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mItemCount++;
                        mItemList.add(0, "List item " + mItemCount);
                        mAdapter.notifyDataSetChanged();
                        mERecyclerView.updateInjectedViewPositions(1);
                        mERecyclerView.refreshComplete();
                    }
                }, 2000);
            }
        });

        // Set quick return header
        mERecyclerView.setQuickReturnHeader(
                mQuickReturnHeaderTextView, getResources().getDimensionPixelSize(R.dimen.quick_return_header_view_height)
        );

        // Set quick return footer
        mERecyclerView.setQuickReturnFooter(
                mQuickReturnFooterTextView, getResources().getDimensionPixelSize(R.dimen.quick_return_footer_view_height)
        );

        mERecyclerView.setQuickReturnViewsSnapable(true);

        // Add headers
        View headerView1 = LayoutInflater.from(this).inflate(R.layout.view_header, mERecyclerView, false);
        View headerView2 = LayoutInflater.from(this).inflate(R.layout.view_header, mERecyclerView, false);
        TextView headerTextView1 = ButterKnife.findById(headerView1, R.id.view_header_text);
        headerTextView1.setText("HeaderView1");
        TextView headerTextView2 = ButterKnife.findById(headerView2, R.id.view_header_text);
        headerTextView2.setText("HeaderView2");
        headerTextView2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        mERecyclerView.addHeaderView(headerView1);
        mERecyclerView.addHeaderView(headerView2);

        // Add footers
        View footerView1 = LayoutInflater.from(this).inflate(R.layout.view_footer, mERecyclerView, false);
        View footerView2 = LayoutInflater.from(this).inflate(R.layout.view_footer, mERecyclerView, false);
        TextView footerTextView1 = ButterKnife.findById(footerView1, R.id.view_footer_text);
        footerTextView1.setText("FooterView1");
        TextView footerTextView2 = ButterKnife.findById(footerView2, R.id.view_footer_text);
        footerTextView2.setText("FooterView2");
        footerTextView2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        mERecyclerView.addFooterView(footerView1);
        mERecyclerView.addFooterView(footerView2);

        // Add load more
        mERecyclerView.setLoadMoreView(DefaultLoadMoreFooter.getResourceId(), null);
        mERecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (mLoadTime < 2) {
                            mItemList.add(mItemCount, "List item " + mItemCount);
                            mItemCount++;
                            mLoadTime++;
                            mAdapter.notifyDataSetChanged();
                        }

                        mERecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });

        mAdapter = new Adapter();
        mERecyclerView.setAdapter(mAdapter);

        // Inject views
        View injectedView1 = LayoutInflater.from(this).inflate(R.layout.view_injected, mERecyclerView, false);
        View injectedView2 = LayoutInflater.from(this).inflate(R.layout.view_injected, mERecyclerView, false);
        TextView injectedText1 = ButterKnife.findById(injectedView1, R.id.injected_text_view);
        injectedText1.setText("InjectedView1");
        TextView injectedText2 = ButterKnife.findById(injectedView2, R.id.injected_text_view);
        injectedText2.setText("InjectedView2");
        mERecyclerView.injectView(injectedView1, 6);
        mERecyclerView.injectView(injectedView2, 12);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Call remove listeners on destroy if and quick return views are added
        mERecyclerView.removeListeners();
        ButterKnife.unbind(this);
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.itemTextView.setText(mItemList.get(position));
        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            @Bind(android.R.id.text1)
            TextView itemTextView;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}

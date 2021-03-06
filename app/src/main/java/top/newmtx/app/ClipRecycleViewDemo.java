package top.newmtx.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import top.newmtx.clip.ClipHorizontalScrollView;
import top.newmtx.clip.ClipRecycleView;

public class ClipRecycleViewDemo extends Activity {

    private Context mContext=ClipRecycleViewDemo.this;
    private ClipRecycleView rv_list;
    private Adapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ui_clip_recycle_demo);


        rv_list= (ClipRecycleView) findViewById(R.id.crv_list);

        //设置是否在切换item首先检查拦截展开事件
        rv_list.setInterceptBeforeTouch(true);

        height=0;
        width=getWindowManager().getDefaultDisplay().getWidth();

        rv_list.setLayoutManager(new LinearLayoutManager(this));

        mRecycleAdapter=new Adapter();

        rv_list.setAdapter(mRecycleAdapter);
        ((SimpleItemAnimator)rv_list.getItemAnimator()).setSupportsChangeAnimations(false);
        initRecycleView();
    }


    private long lastTime=-1;
    private float last_x=-1;

    private void initRecycleView(){
        rv_list.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        long l = System.currentTimeMillis();
                        if(l-lastTime<300){
                            if(rv_list.getScaleX()>1){
                                rv_list.setScaleX(1);
                                rv_list.setScaleY(1);
                            }else{
                                last_x=event.getX();
                                rv_list.setScaleX(2);
                                rv_list.setScaleY(2);
                            }
                            return true;
                        }
                        lastTime=l;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(rv_list.getScaleX()>1){
                            rv_list.scrollBy((int) (event.getX()-last_x),0);
                            last_x=event.getX();
                            return true;
                        }
                }
                return false;
            }
        });
    }



    private int width=80;

    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate =null;
            if(viewType==HEAD_TYPE){
                inflate =new TextView(mContext);
                return new ViewHolder(inflate);
            }else{
                inflate = LayoutInflater.from(mContext).inflate(R.layout.list_item_h, null);
                ItemViewHolder itemViewHolder = new ItemViewHolder(inflate);
                return itemViewHolder;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ViewHolder){
                Log.i("ClipRecycleViewDemo","holder:holder change");
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,height));
            }else if(holder instanceof ItemViewHolder){
                ((ItemViewHolder) holder).mHorizontalScrollView.scrollTo(0,0);
            }
        }

        @Override
        public int getItemCount() {
            return 100+1;
        }

        private final int HEAD_TYPE=-3;
        private final int ITEM_TYPE=-4;

        @Override
        public int getItemViewType(int position) {
            if(position==0){
                return HEAD_TYPE;
            }else{
                return ITEM_TYPE;
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            private ClipHorizontalScrollView mHorizontalScrollView;
            private ImageView mImageView;
            private TextView tv_delete,tv_add;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mHorizontalScrollView= (ClipHorizontalScrollView) itemView.findViewById(R.id.hsv_item);
                mImageView= (ImageView) itemView.findViewById(R.id.iv_img);
                tv_delete= (TextView) itemView.findViewById(R.id.tv_delete);
                tv_add= (TextView) itemView.findViewById(R.id.tv_add);
                ClipHorizontalScrollView.onRelayoutGrandson(mImageView,width);
            }
        }
    }

    private int height=200;
}

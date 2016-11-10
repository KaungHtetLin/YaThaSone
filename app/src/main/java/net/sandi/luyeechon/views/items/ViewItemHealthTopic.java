package net.sandi.luyeechon.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.sandi.luyeechon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by UNiQUE on 9/20/2016.
 */
public class ViewItemHealthTopic extends FrameLayout {

    @BindView(R.id.tv_health_topic)
    TextView tvHealthTopic;

    public ViewItemHealthTopic(Context context) {
        super(context);
    }

    public ViewItemHealthTopic(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemHealthTopic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String healthTopic) {
        tvHealthTopic.setText(healthTopic);
    }
}

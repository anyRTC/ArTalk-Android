package io.anyrtc.talkdemo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class LogAdapter : BaseQuickAdapter<String,BaseViewHolder>(android.R.layout.simple_list_item_1) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(android.R.id.text1, item)
    }

    override fun addData(data: String) {
        super.addData(data)
        recyclerView.scrollToPosition(itemCount-1)
    }
}
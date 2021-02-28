package com.example.android_arch.jetpack

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_arch.R
import com.example.android_arch.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private var dataBind: ActivityRecyclerBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = DataBindingUtil.setContentView<ActivityRecyclerBinding>(
            this,
            R.layout.activity_recycler
        )
        dataBind?.myRecycler?.apply {
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            val dataList = ArrayList<String>()
            dataList.add("hello")
            dataList.add("world")
            dataList.add("event")
            dataList.add("bus")
            adapter = MyRecyclerAdapter(dataList, this@RecyclerActivity)
            addItemDecoration(MyItemDecoration(this@RecyclerActivity))
        }

        runOnUiThread {

        }
    }

    class MyRecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.item_name)
    }

    class MyRecyclerAdapter(val dataList: List<String>, val context: Context) :
        RecyclerView.Adapter<MyRecyclerHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
            return MyRecyclerHolder(View.inflate(context, R.layout.item_recycler, null))
        }

        override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
            holder.tvName.text = dataList[position]
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }

    class MyItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
        private val array = intArrayOf(android.R.attr.listDivider)
        private val divider: Drawable? = null

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

            drawHorizontal(c, parent)
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(0, 0, 0, 10)
        }

        fun drawHorizontal(c: Canvas, parent: RecyclerView) {
            val a = context.obtainStyledAttributes(array)
            a.getDrawable(0)
            a.recycle()

            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            for (child: View in parent.children) {
                val layoutParams = child.layoutParams as RecyclerView.LayoutParams

                divider?.setBounds(
                    left,
                    layoutParams.bottomMargin,
                    right,
                    layoutParams.bottomMargin + divider.intrinsicHeight
                )
                divider?.draw(c)
            }
        }
    }
}
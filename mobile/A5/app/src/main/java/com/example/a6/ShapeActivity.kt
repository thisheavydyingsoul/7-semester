package com.example.a6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color

class ShapeActivity : AppCompatActivity() {
    private lateinit var expandableListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        expandableListView = ExpandableListView(this)

        val shapes = listOf("Circle", "Square", "Triangle")
        val sizes = listOf(
            listOf("Small", "Medium", "Large"),
            listOf("Small", "Medium", "Large"),
            listOf("Small", "Medium", "Large")
        )

        val groupData = shapes.map { mapOf("shape" to it) }
        val childData = sizes.map { sizeList -> sizeList.map { size -> mapOf("size" to size) } }

        val adapter: ExpandableListAdapter = object : BaseExpandableListAdapter() {
            override fun getGroupCount(): Int = groupData.size

            override fun getChildrenCount(groupPosition: Int): Int = childData[groupPosition].size

            override fun getGroup(groupPosition: Int): Any = groupData[groupPosition]

            override fun getChild(groupPosition: Int, childPosition: Int): Any =
                childData[groupPosition][childPosition]

            override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

            override fun getChildId(groupPosition: Int, childPosition: Int): Long =
                childPosition.toLong()

            override fun hasStableIds(): Boolean = true

            override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: TextView(parent?.context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                (view as TextView).text = (getGroup(groupPosition) as Map<*, *>)["shape"].toString()
                return view
            }

            override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: TextView(parent?.context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                (view as TextView).text = (getChild(groupPosition, childPosition) as Map<*, *>)["size"].toString()
                return view
            }

            override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
        }

        expandableListView.setAdapter(adapter)


        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val selectedShape = shapes[groupPosition]
            val selectedSize = sizes[groupPosition][childPosition]
            val color = intent.getIntExtra("color", Color.rgb(0, 0, 0),)


            // Переход к новой активности с передачей данных
            val intent = Intent(this, DrawShapeActivity::class.java).apply {
                putExtra("shape", selectedShape)
                putExtra("size", selectedSize)
                putExtra("color", color)
            }
            startActivity(intent)
            true
        }

        mainLayout.addView(expandableListView)
        setContentView(mainLayout)
    }
}

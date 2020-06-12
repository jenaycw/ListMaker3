package com.raywenderlich.listmaker2

import android.os.Bundle
import android.text.InputType
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.JdkConstants



class MainActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView
    val listDataManager: ListDataManager = ListDataManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        val lists = listDataManager.readLists()
        listsRecyclerView =
            findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists)


        fab.setOnClickListener {
            showCreateListDialog()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)
        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            dialog.dismiss()


            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)
            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recyclerAdapter.addList(list)


        }


        builder.create().show()
    }
}



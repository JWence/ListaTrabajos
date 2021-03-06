package TodoItems

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listatrabajos.R
import com.example.listatrabajos.TodoAddEdit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_todo_item.view.*

class TodoListAdapter (val items:MutableList<TodoItemData>, val context:Context, val onClickEdit:(item:TodoItemData) -> Unit, val onClickDelete:(item:TodoItemData) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoHolder>(){

    var todoDbController:TodoDb.Controller? = null
    class TodoHolder(val itemTemplate:View):RecyclerView.ViewHolder(itemTemplate) {
        fun render(item:TodoItemData, context: Context, onClickEdit:(item:TodoItemData) -> Unit, onClickDelete:(item:TodoItemData) -> Unit) {
            /* Datos del template */
            itemTemplate.vTodoTitle.text = item.title
            itemTemplate.vTodoMessage.text = item.message
            itemTemplate.vTodoDate.text = item.date
            Picasso.get().load(item.imageUri).into(itemTemplate.vTodoImage)
            /* Button Listeners */
            itemTemplate.vTodoEdit.setOnClickListener {

                onClickEdit(item)
            }
            itemTemplate.vTodoDone.setOnClickListener {
                onClickDelete(item)
                //Pruebas
                println("=============================")
                println("Done")
                println(item)
                println(item.id)
                println("=============================")


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoHolder(layoutInflater.inflate(R.layout.activity_main_todo_item, parent, false))
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.render(items[position], context, onClickEdit, onClickDelete)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
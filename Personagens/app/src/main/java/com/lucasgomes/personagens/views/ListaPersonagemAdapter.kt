package com.lucasgomes.personagens.views

import android.widget.TextView
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.lucasgomes.personagens.R
import com.lucasgomes.personagens.models.Personagem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.toast

/**
 * Created by Lucas Gomes on 13/09/2017.
 */
class ListaPersonagemAdapter(private val dataSet: ArrayList<Personagem>, internal var mContext: Context, val onClick : (Personagem) -> Unit) : ArrayAdapter<Personagem>(mContext, R.layout.item_personagem, dataSet) {

    // View lookup cache
    private class ViewHolder {
        internal var txtId : TextView? = null
        internal var info : ImageView? = null
        internal var txtName : TextView? = null
        internal var txtRealName : TextView? = null
        internal var itemCell : ConstraintLayout? = null
    }

//    override fun onClick(v: View) {
//
//        val position = v.tag as Int
//        val `object` = getItem(position)
//        val dataModel = `object` as Personagem
//
//        when (v.id) {
//            R.id.item_info -> {
//                val intent = Intent(context, PersonagemDetalheActivity::class.java)
//                intent.putExtra(PersonagemDetalheActivity.ID_KEY, v.id)
//                context.startActivity(intent)
//                //context.toast("${dataModel.Nome}")
//            }
//        }
//    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        // Get the data item for this position
        val dataModel = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        val viewHolder: ViewHolder // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.item_personagem, parent, false)
            viewHolder.txtId = convertView!!.findViewById(R.id.text_id)
            viewHolder.info = convertView.findViewById(R.id.item_info)
            viewHolder.txtName = convertView.findViewById(R.id.text_name)
            viewHolder.txtRealName = convertView.findViewById(R.id.text_real_name)
            viewHolder.itemCell = convertView.findViewById(R.id.item_cell)
            //viewHolder.info!!.setOnClickListener({ onClick(dataModel) })
            viewHolder.itemCell!!.setOnClickListener({ onClick(dataModel) })

            convertView.tag = viewHolder

        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        Picasso.with(context)
                .load(dataModel.FotoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.info)

        viewHolder.txtName!!.text = dataModel!!.Nome
        viewHolder.txtId!!.text = dataModel.ID.toString()
        viewHolder.txtRealName!!.text = dataModel.NomeReal

        return convertView
    }
}
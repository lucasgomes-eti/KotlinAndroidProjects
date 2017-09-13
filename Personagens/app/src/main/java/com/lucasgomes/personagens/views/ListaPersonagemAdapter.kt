package com.lucasgomes.personagens.views

import android.widget.TextView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.lucasgomes.personagens.R
import com.lucasgomes.personagens.models.Personagem
import com.squareup.picasso.Picasso


/**
 * Created by Lucas Gomes on 13/09/2017.
 */
class ListaPersonagemAdapter(private val dataSet: ArrayList<Personagem>, internal var mContext: Context) : ArrayAdapter<Personagem>(mContext, R.layout.item_personagem, dataSet) {

    // View lookup cache
    private class ViewHolder {
        internal var txtName: TextView? = null
        //internal var txtRealName: TextView? = null
        internal var info: ImageView? = null
    }


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
            viewHolder.info = convertView!!.findViewById(R.id.item_info)
            viewHolder.txtName = convertView!!.findViewById(R.id.name)
            //viewHolder.txtRealName = convertView!!.findViewById(R.id.real_name)

            convertView!!.setTag(viewHolder)
        } else {
            viewHolder = convertView!!.getTag() as ViewHolder
        }

        Picasso.with(context)
                .load(dataModel.FotoUrl)
                .placeholder(android.R.drawable.ic_dialog_info)
                .error(android.R.drawable.stat_notify_error)
                .into(viewHolder.info)

        viewHolder.txtName!!.text = dataModel!!.Nome
        //viewHolder.txtRealName!!.text = dataModel.NomeReal

        return convertView
    }
}
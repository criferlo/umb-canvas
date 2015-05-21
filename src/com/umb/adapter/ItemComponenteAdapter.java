package com.umb.adapter;

import java.util.List;

import com.umb.R;
import com.umb.datos.entidades.EntidadComponente;
import com.umb.util.Constantes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemComponenteAdapter extends BaseAdapter {

	private Context context;
	private List<Object> items;

	public ItemComponenteAdapter(Context context, List<Object> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if (convertView == null) {
			// Create a new view into the list.
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.item_componente, parent, false);
		}
		TextView texto = (TextView) rowView
				.findViewById(R.id.txtTextoComponente);
		EntidadComponente item = (EntidadComponente) this.items.get(position);
		texto.setText(item.getDescripcion());

		if (item.getColor().equals(Constantes.AMARILLO)) {
			texto.setBackground(context.getResources().getDrawable(
					R.drawable.canvas_amarillo));
		}
		if (item.getColor().equals(Constantes.AZUL)) {
			texto.setBackground(context.getResources().getDrawable(
					R.drawable.canvas_azul));
		}
		if (item.getColor().equals(Constantes.VERDE)) {
			texto.setBackground(context.getResources().getDrawable(
					R.drawable.canvas_verde));
		}
		if (item.getColor().equals(Constantes.ROJO)) {
			texto.setBackground(context.getResources().getDrawable(
					R.drawable.canvas_rojo));
		}

		return rowView;
	}

}

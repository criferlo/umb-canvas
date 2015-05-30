package com.umb.adapter;

import java.util.List;

import com.umb.R;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.entidades.EntidadComponente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemCanvasAdapter extends BaseAdapter{

	private Context context;
	private List<Object> items;
	
	
	public ItemCanvasAdapter(Context context, List<Object> items) {
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
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.item_canvas, parent, false);
		}
		
		TextView txtNombre = (TextView) rowView.findViewById(R.id.txtNombreCanvas);
		TextView txtDescripción = (TextView) rowView.findViewById(R.id.txtDescripcionCanvas);
		EntidadCanvas item = (EntidadCanvas) this.items.get(position);
		
		txtNombre.setText(item.getNombre());
		txtDescripción.setText(item.getDescripcion());
		
		return rowView;
	}

}

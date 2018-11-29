package ctrlcctrlv.happytraveller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import ctrlcctrlv.happytraveller.R;
import ctrlcctrlv.happytraveller.model.PlaceData;


public class ListItemAdapter extends ArrayAdapter<PlaceData> implements View.OnClickListener
{
    private ArrayList<PlaceData> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder
    {
        TextView txtName;
        TextView txtAddress;
        ImageView imgView;
        //add more data var-components px : img info etc..
    }



    public ListItemAdapter(ArrayList<PlaceData> data, Context context)
    {
        super(context, R.layout.list_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override //edw 8a mpei to listener gia to detail btn
    public void onClick(View view)
    {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlaceData placeData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view, parent, false);
            //init ta components apo to list_view.xml ,add more pio meta
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtAddress=(TextView) convertView.findViewById(R.id.address);
            viewHolder.imgView=(ImageView)convertView.findViewById(R.id.imageView);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(placeData.getName());
        viewHolder.txtAddress.setText(placeData.getAddress());
        //when need show all img not complete yet
        //Picasso.with(getContext()).load(placeData.getPlacePhotos().get(0).getImgUrl()).into(viewHolder.imgView);

        //check if no default img show error img
        if(placeData.getDefaultImg().getPhotoReference()==null)
        {
            Picasso.with(getContext()).load(R.drawable.no_photo_available).into(viewHolder.imgView);
        }
        else
         { //else load default place img
             Picasso.with(getContext()).load(placeData.getDefaultImg().getImgUrl()).into(viewHolder.imgView);
        }

        //  viewHolder.info.setOnClickListener(this);
        // viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


}

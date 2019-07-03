package com.example.zoosuport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zoosuport.dataBase.SqlLite;
import com.example.zoosuport.entitys.Animal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    Button form_search, form_cancel, form_accept;
    TextView title, textViewID, textVientryDate, textViewHabitat, textViewName;
    RadioGroup RadioGroupSex;
    Spinner spinnerClassification, spinnerSpecies;
    View view;
    SqlLite sqlite;
    ArrayAdapter<CharSequence> adapter, adapter1;
    String b, a;

    public CreateFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create, container, false);
        form_search = view.findViewById(R.id.form_search);
        form_search.setVisibility(View.INVISIBLE);
        title = view.findViewById(R.id.form_title);
        title.setText("Agregar nuevo animal");


        form_cancel = view.findViewById(R.id.form_cancel);
        form_accept = view.findViewById(R.id.form_accept);

        form_cancel.setOnClickListener(this);
        form_accept.setOnClickListener(this);


        textViewID = view.findViewById(R.id.form_id);
        textVientryDate = view.findViewById(R.id.form_date_entry);
        spinnerClassification = view.findViewById(R.id.form_clasification);
        textViewHabitat = view.findViewById(R.id.form_habitat);
        RadioGroupSex = view.findViewById(R.id.form_sex);
        textViewName = view.findViewById(R.id.form_name);

        spinnerClassification = view.findViewById(R.id.form_clasification);
        spinnerSpecies = view.findViewById(R.id.form_clasification1);

        sqlite = new SqlLite(this.view.getContext());


        adapter = ArrayAdapter.createFromResource(this.view.getContext(),
            R.array.opcions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassification.setAdapter(adapter);


        spinnerClassification.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String opcion = String.valueOf(spinnerClassification.getSelectedItemId());
                    int op = Integer.parseInt(opcion);
                    System.out.println(opcion);
                    switch (op) {
                        case 0:
                            break;
                        case 1:
                            b = adapter.getItem(1).toString();

                            adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                                R.array.o1, android.R.layout.simple_spinner_item);


                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerSpecies.setAdapter(adapter1);
                            break;
                        case 2:
                            b = adapter.getItem(2).toString();


                            adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                                R.array.o2, android.R.layout.simple_spinner_item);


                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerSpecies.setAdapter(adapter1);

                            break;
                        case 3:
                            b = adapter.getItem(3).toString();


                            adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                                R.array.o3, android.R.layout.simple_spinner_item);


                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerSpecies.setAdapter(adapter1);
                            break;
                        case 4:
                            b = adapter.getItem(4).toString();


                            adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                                R.array.o4, android.R.layout.simple_spinner_item);


                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerSpecies.setAdapter(adapter1);
                            break;

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            }
        );


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //action button
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.form_accept:

                String id = "";
                String entryDate = "";
                String classification = "";
                String habitat = "";
                String sex = "";
                String species = "";
                String name = "";
                Animal newAnimal=null;
                try {
                    RadioButton sexButton = this.view.findViewById((RadioGroupSex).getCheckedRadioButtonId());
                    id = textViewID.getText().toString();
                    entryDate = textVientryDate.getText().toString();
                    classification = spinnerClassification.getSelectedItem().toString();
                    habitat = textViewHabitat.getText().toString();
                    sex = sexButton.getText().toString();
                    species = spinnerSpecies.getSelectedItem().toString();
                    name = textViewName.getText().toString();
                    newAnimal = new Animal(id, classification, species, name, sex, entryDate, habitat, "food");

                } catch (Exception ex) {
                    newAnimal=null;

                }
                if (newAnimal!=null&&
                    validation(id) && validation(entryDate) &&
                    validation(classification) && validation(species) &&
                    validation(habitat) && validation(sex) && validation(name)
                ) {
                    sqlite.opeen();
                    if (sqlite.addRegister(newAnimal.insert(), "Animals")) {
                        Toast.makeText(this.view.getContext(), "Registro guardado", Toast.LENGTH_SHORT).show();
                        sqlite.cerrar();
                    } else {
                        Toast.makeText(this.view.getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(this.view.getContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.form_cancel:
                Toast.makeText(view.getContext(), "Cancelar", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public boolean validation(String date) {

        if (date.trim().length() > 0) {
            return true;
        }
        return false;
    }
}

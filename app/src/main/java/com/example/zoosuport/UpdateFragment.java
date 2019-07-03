package com.example.zoosuport;

import android.content.Context;
import android.database.Cursor;
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
 * {@link UpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener {


    Button form_search, form_cancel, form_accept;
    TextView form_textView_search;
    TextView title;
    View view;
    SqlLite sqlite;
    TextView textViewID, textVientryDate, textViewHabitat, textViewName;
    RadioGroup RadioGroupSex;
    Spinner spinnerClassification, spinnerSpecies;
    ArrayAdapter<CharSequence> adapter, adapter1;
    Animal animal = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update, container, false);
        form_search = view.findViewById(R.id.form_search);
        title = view.findViewById(R.id.form_title);
        title.setText("Modificar animal");


        form_cancel = view.findViewById(R.id.form_cancel);
        form_accept = view.findViewById(R.id.form_accept);
        form_search = view.findViewById(R.id.form_search);
        form_cancel.setOnClickListener(this);
        form_accept.setOnClickListener(this);
        form_search.setOnClickListener(this);
        sqlite = new SqlLite(view.getContext());

        textViewID = view.findViewById(R.id.form_id);
        textVientryDate = view.findViewById(R.id.form_date_entry);
        spinnerClassification = view.findViewById(R.id.form_clasification);
        textViewHabitat = view.findViewById(R.id.form_habitat);
        RadioGroupSex = view.findViewById(R.id.form_sex);
        textViewName = view.findViewById(R.id.form_name);
        spinnerSpecies = view.findViewById(R.id.form_clasification1);

        spinnerClassification.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String opcion = String.valueOf(spinnerClassification.getSelectedItemId());
                    int op = Integer.parseInt(opcion);
                    cargarSegundoSppiner(op);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.form_accept:

                if (animal != null) {

                    Toast.makeText(view.getContext(), "actualizar Aceptar", Toast.LENGTH_SHORT).show();
                    RadioButton sexButton = this.view.findViewById((RadioGroupSex).getCheckedRadioButtonId());
                    String id = textViewID.getText().toString();
                    String entryDate = textVientryDate.getText().toString();
                    String classification = spinnerClassification.getSelectedItem().toString();
                    String habitat = textViewHabitat.getText().toString();
                    String sex = sexButton.getText().toString();
                    String species = spinnerSpecies.getSelectedItem().toString();
                    String name = textViewName.getText().toString();
                    Animal newAnimal = new Animal(id, classification, species, name, sex, entryDate, habitat, "food");

                    String modification = sqlite.upDate(id, classification, species, name, sex, entryDate, habitat, "food");
                    if (modification.equals("Modificado")) {
                        Toast.makeText(getContext(), "Registro guardado", Toast.LENGTH_SHORT).show();

                        animal = null;
                    } else {
                        Toast.makeText(getContext(), "No :c", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Animal no seleccionado", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.form_cancel:
                Toast.makeText(view.getContext(), "actualizar Cancelar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.form_search:
                Toast.makeText(view.getContext(), "Buscando", Toast.LENGTH_SHORT).show();
                sqlite.opeen();
                String search = textViewID.getText().toString();
                Cursor cursor = sqlite.getCant(search, "ANIMALS");
                if (cursor != null) {
                    cursor.moveToFirst();
                    if (cursor.getCount() > 0) {
                        animal = sqlite.converteAnimal(cursor);
                    } else {
                        animal = null;
                    }
                }


                if (animal != null) {

                    textVientryDate.setText(animal.getFECHA_ING());

                    adapter = ArrayAdapter.createFromResource(this.view.getContext(),
                        R.array.opcions, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerClassification.setAdapter(adapter);

                    int position = position(animal.getCLASIFICATION(), spinnerClassification);
                    spinnerClassification.setSelection(position, true);
                    cargarSegundoSppiner(position);
                    int position2 = position(animal.getESPECIE(), spinnerSpecies);
                    spinnerSpecies.setSelection(position2, true);
                    textViewHabitat.setText(animal.getHABITAT());
                    radioGroup(RadioGroupSex, animal.getSEXO());
                    textViewName.setText(animal.getNOMBRE());


                    Toast.makeText(view.getContext(), "Encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    Animal animal = null;
                    Toast.makeText(view.getContext(), "No se encontro", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public int position(String date, Spinner spinner) {
        int position = -1;

        int end = spinner.getCount();
        for (int x = 0; x < spinner.getCount(); x++) {

            if (spinner.getItemAtPosition(x).toString().equals(date)) {
                return x;
            }
        }
        return position;
    }

    public int radioGroup(RadioGroup group, String date) {

        for (int y = 0; y < group.getChildCount(); y++) {
            RadioButton rb = (RadioButton) group.getChildAt(y);
            if (rb.getText().toString().equals(date)) {
                rb.setChecked(true);
                return y;
            }
        }
        return -1;
    }


    public void cargarSegundoSppiner(int op) {

        switch (op) {
            case 0:
                break;
            case 1:
                adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                    R.array.o1, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpecies.setAdapter(adapter1);
                break;
            case 2:

                adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                    R.array.o2, android.R.layout.simple_spinner_item);


                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpecies.setAdapter(adapter1);

                break;
            case 3:


                adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                    R.array.o3, android.R.layout.simple_spinner_item);


                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpecies.setAdapter(adapter1);
                break;
            case 4:
                adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                    R.array.o4, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpecies.setAdapter(adapter1);
                break;

        }
        if (animal != null) {
            int position2 = position(animal.getESPECIE(), spinnerSpecies);
            spinnerSpecies.setSelection(position2, true);
        }


    }
}

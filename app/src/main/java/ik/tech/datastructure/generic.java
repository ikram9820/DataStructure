package ik.tech.datastructure;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Html;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

public class generic extends AppCompatActivity {
    Array arr;
    private Spinner insSpin,delSpin,sortSpin,searSpin;
    private EditText data,index;
    private TextView code,arrayData,algo;
    private Button algoTextBtn,codeTextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genr);


        arr=new Array(10);
        algoTextBtn = (Button)findViewById(R.id.algoTextBtn);
        codeTextBtn=(Button)findViewById(R.id.codeTextBtn);
        insSpin= (Spinner)findViewById(R.id.ais);
        delSpin= (Spinner)findViewById(R.id.ads);
        sortSpin= (Spinner)findViewById(R.id.asos);
        searSpin= (Spinner)findViewById(R.id.ass);
        data=(EditText)findViewById(R.id.det);

        index=(EditText)findViewById(R.id.iet);
        code=(TextView)findViewById(R.id.acodetext);
        algo = (TextView)findViewById(R.id.aAlgoetext);
        arrayData=(TextView)findViewById(R.id.adatatext);

        spinner();

    }


    public void showData(){
        data.setVisibility(View.VISIBLE);
        index.setVisibility(View.GONE);
    }
    public void showIndex(){
        data.setVisibility(View.GONE);
        index.setVisibility(View.VISIBLE);
    }

    public  void showDataIndex(){
        data.setVisibility(View.VISIBLE);
        index.setVisibility(View.VISIBLE);
    }

    public void hideDataIndex(){
        data.setVisibility(View.GONE);
        index.setVisibility(View.GONE);
    }

    public void spinner(){

        insSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ch=insSpin.getSelectedItem().toString();

                if (ch.equals("insert at specific index")) {
                    showDataIndex();
                } else{
                    showData();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(generic.this,"nothing is selected",Toast.LENGTH_SHORT).show();
            }
        });

        delSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ch=delSpin.getSelectedItem().toString();

                if (ch.equals("delete from specific index")) {
                    showIndex();
                }
                else if(ch.equals("delete specific item")){
                    showData();
                }
                else{
                    hideDataIndex();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText( generic.this,insSpin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        searSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showData();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText( generic.this,insSpin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }//end spinner()


    public void arrayInsertion(View view) {
        algo.setText(Html.fromHtml(getString((R.string.insertion_algo))));
        String ch = insSpin.getSelectedItem().toString();
        if(ch.equals("insert at specific index")) {
            code.setText(Html.fromHtml(getString((R.string.insert_at_code))));
            try {
                Toast.makeText( this,  arr.insert(Integer.parseInt(data.getText().toString()), Integer.parseInt(index.getText().toString())),
                        Toast.LENGTH_SHORT).show();;
            } catch (Exception e) {
                showDataIndex();
                Toast.makeText( this, "please enter data and index  \n" , Toast.LENGTH_SHORT).show();
            }

        }
        else if(ch.equals("insert at first")) {
            code.setText(Html.fromHtml(getString((R.string.insert_first_code))));
            try {
                Toast.makeText( this,  arr.insertFirst(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText( this, "please enter data  \n" , Toast.LENGTH_SHORT).show();
                showData();
            }
        }
        else if(ch.equals("insert at last")) {

            code.setText(Html.fromHtml(getString((R.string.insert_last_code))));
            try {
                Toast.makeText( this, arr.insertLast(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText( this, "please enter data \n" , Toast.LENGTH_SHORT).show();
                showData();
            }
        }
        else if(ch.equals("insert in sorted array")) {

            code.setText(Html.fromHtml(getString((R.string.insert_in_sorted_code))));
            arr.insertionSort();
            try {
                Toast.makeText( this,  arr.insertInSorted(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText( this, "please enter data \n" , Toast.LENGTH_SHORT).show();
                showData();
            }
        }
        else
            Toast.makeText( this,"\""+ch+"\"", Toast.LENGTH_SHORT).show();

        arrayData.setText(arr.traverse());
        data.setText("");
        index.setText("");
    }//end arrayInsertion()

    public void arrayDeletion(View view) {
        algo.setText(Html.fromHtml(getString((R.string.deletion_algo))));
        String ch = delSpin.getSelectedItem().toString();
        if(ch.equals("delete from specific index")) {

            code.setText(Html.fromHtml(getString((R.string.delete_at_code))));
            try {
                Toast.makeText( this,  arr.deleteAt(Integer.parseInt(index.getText().toString())),
                        Toast.LENGTH_SHORT).show();;
            } catch (Exception e) {
                Toast.makeText( this, "please enter index \n", Toast.LENGTH_SHORT).show();
                showIndex();
            }

        }
        else if(ch.equals("delete first")) {

            code.setText(Html.fromHtml(getString((R.string.delete_first_code))));
            Toast.makeText( this,  arr.deleteFirst(), Toast.LENGTH_SHORT).show();
            hideDataIndex();
        }
        else if(ch.equals("delete last")) {

            code.setText(Html.fromHtml(getString((R.string.delete_last_code))));
            Toast.makeText(this, arr.deleteLast(), Toast.LENGTH_SHORT).show();
            hideDataIndex();
        }
        else if(ch.equals("delete specific item")) {

            code.setText(Html.fromHtml(getString((R.string.delete_item_code))));
            try {
                Toast.makeText(this,  arr.delete(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "please enter data  \n" .toString(), Toast.LENGTH_SHORT).show();
                showData();
            }
        }
        else
            Toast.makeText(this,"\""+ch+"\"", Toast.LENGTH_SHORT).show();

        arrayData.setText(arr.traverse());
        data.setText("");
        index.setText("");
    }

    public void arrayGetAt(View view) {
        algo.setText(Html.fromHtml(getString((R.string.get_at_algo))));
        code.setText(Html.fromHtml(getString((R.string.get_at_code))));
        if(index.getVisibility()!=view.VISIBLE){
            data.setVisibility(view.GONE);
            index.setVisibility(view.VISIBLE);
        }
        else {
            try {
                Toast.makeText(this, arr.getAt(Integer.parseInt(index.getText().toString())),
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "please enter index\n"  , Toast.LENGTH_SHORT).show();
                showIndex();
            }

            index.setText("");
        }
    }//end arrayGetAt()

    public void arraySort(View view) {
        String ch = sortSpin.getSelectedItem().toString();

        if(ch.equals("insertion sort")) {
            algo.setText(Html.fromHtml(getString((R.string.insert_algo))));
            code.setText(Html.fromHtml(getString((R.string.insertion_sort_code))));
            arr.insertionSort();
            Toast.makeText( this, "array is sorted by insertion sort", Toast.LENGTH_SHORT).show();
        }
        else if(ch.equals("bubble sort")) {
            algo.setText(Html.fromHtml(getString((R.string.bubble_algo))));
            code.setText(Html.fromHtml(getString((R.string.bubble_sort_code))));
            arr.bubbleSort();
            Toast.makeText( this, "array is sorted by bubble sort", Toast.LENGTH_SHORT).show();
        }
        else if(ch.equals("selection sort")) {
            algo.setText(Html.fromHtml(getString((R.string.selection_algo))));
            code.setText(Html.fromHtml(getString((R.string.selection_sort_code))));
            arr.selectionSort();
            Toast.makeText( this, "array is sorted by selection sort", Toast.LENGTH_SHORT).show();
        }
        else if(ch.equals("quick sort")) {
            algo.setText(Html.fromHtml(getString((R.string.quick_algo))));
            code.setText(Html.fromHtml(getString((R.string.quick_sort_code))));
            arr.quickSort( 0 , arr.getNelement()-1 );
            Toast.makeText( this,"array is sorted by quick sort", Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText( this, "there is no such type of sort function", Toast.LENGTH_SHORT).show();
        arrayData.setText(arr.traverse());

    }//end arraySort()

    public void arraySearch(View view) {
        String ch = searSpin.getSelectedItem().toString();
        if(ch.equals("linear search")) {
            algo.setText(Html.fromHtml(getString((R.string.linear_algo))));
            code.setText(Html.fromHtml(getString((R.string.linear_search_code))));
            try {
                Toast.makeText( this,  arr.search(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText( this, "please enter data and index  \n" .toString(), Toast.LENGTH_SHORT).show();
                showData();
            }
        }
        else if(ch.equals("binary search")) {
            algo.setText(Html.fromHtml(getString((R.string.binary_algo))));
            code.setText(Html.fromHtml(getString((R.string.binary_search_code))));
            arr.insertionSort();
            try {
                Toast.makeText( this, arr.bSearch(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText( this, "please enter data and index  \n" .toString(), Toast.LENGTH_SHORT).show();
                showData();
            }
        }
        arrayData.setText(arr.traverse());
        data.setText("");
    }


    public void showCode(View view){
        if(codeTextBtn.getText().equals("show code")) {

            codeTextBtn.setText("hide code");
            algoTextBtn.setText("show algorithm");
            codeTextBtn.setBackgroundColor(getResources().getColor(R.color.blackLight));
            codeTextBtn.setTextColor(getResources().getColor(R.color.white));

            algoTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            algoTextBtn.setTextColor(getResources().getColor(R.color.black));

            algo.setVisibility(View.GONE);
            code.setVisibility(View.VISIBLE);

            return;
        }

        codeTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
        codeTextBtn.setText("show code");


        codeTextBtn.setTextColor(getResources().getColor(R.color.black));

        code.setVisibility(View.GONE);
    }

    public void showAlgo(View view){
        if(algoTextBtn.getText().equals("show algorithm")) {

            algoTextBtn.setText("hide algorithm");
            codeTextBtn.setText("show code");

            algoTextBtn.setBackgroundColor(getResources().getColor(R.color.blackLight));
            algoTextBtn.setTextColor(getResources().getColor(R.color.white));

            codeTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            codeTextBtn.setTextColor(getResources().getColor(R.color.black));

            code.setVisibility(View.GONE);
            algo.setVisibility(View.VISIBLE);

            return;
        }

        algoTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
        algoTextBtn.setText("show algorithm");


        algoTextBtn.setTextColor(getResources().getColor(R.color.black));
        algo.setVisibility(View.GONE);
    }



}
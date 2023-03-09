package mod.RockAriful.AndroXStudio;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;

import com.sketchware.remod.R;
import mod.SketchwareUtil; 

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

import org.json.*;

import org.eclipse.jgit.*;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import java.io.BufferedWriter; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.OutputStreamWriter; 
import java.text.MessageFormat; 
import java.util.Date; 
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean; 
 
import org.eclipse.jgit.api.CloneCommand; 
import org.eclipse.jgit.api.Git; 
import org.eclipse.jgit.api.ResetCommand.ResetType; 
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException; 
import org.eclipse.jgit.lib.Constants; 
import org.eclipse.jgit.revwalk.RevCommit; 
import org.eclipse.jgit.transport.CredentialsProvider; 
import org.eclipse.jgit.transport.PushResult; 
import org.eclipse.jgit.transport.RefSpec; 
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.transport.RemoteRefUpdate; 
import org.eclipse.jgit.transport.RemoteRefUpdate.Status; 
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider; 
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.util.FileUtils; 

public class GithubConfigActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> JsonMAP = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear13;
	private LinearLayout all_item_layout;
	private LinearLayout linear14;
        private TextView push_data;
	private TextView create_token;
	private TextView textview1;
	private Switch enable;
	private ImageView imageview2;
	private TextView textview13;
	private LinearLayout is_url;
	private LinearLayout linear12;
	private EditText setRefSpecs;
	private LinearLayout credential_layout;
	private TextView textview7;
	private EditText url;
	private TextView textview11;
	private TextView important_btn;
	private TextView textview5;
	private EditText username;
	private TextView textview6;
	private EditText pass;
    private Intent Ctoken = new Intent();

    private String sc_id ="";
	//boolean&string for fatchin fushing result
	private String Result ="";
	private boolean isSucces = false;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.github_config);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear13 = findViewById(R.id.linear13);
		all_item_layout = findViewById(R.id.all_item_layout);
		linear14 = findViewById(R.id.linear14);
 		push_data = findViewById(R.id.push_data);
		create_token = findViewById(R.id.create_token);
		textview1 = findViewById(R.id.textview1);
		enable = findViewById(R.id.enable);
		imageview2 = findViewById(R.id.imageview2);
		textview13 = findViewById(R.id.textview13);
		is_url = findViewById(R.id.is_url);
		linear12 = findViewById(R.id.linear12);
		setRefSpecs = findViewById(R.id.setRefSpecs);
		credential_layout = findViewById(R.id.credential_layout);
		textview7 = findViewById(R.id.textview7);
		url = findViewById(R.id.url);
		textview11 = findViewById(R.id.textview11);
		important_btn = findViewById(R.id.important_btn);
		textview5 = findViewById(R.id.textview5);
		username = findViewById(R.id.username);
		textview6 = findViewById(R.id.textview6);
		pass = findViewById(R.id.pass);

		push_data.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});

		create_token.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Ctoken.setAction(Intent.ACTION_VIEW);
				Ctoken.setData(Uri.parse("https://github.com/settings/tokens/new"));
				startActivity(Ctoken);
			}
		});
		
		enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					if (url.getText().toString().trim().equals("")) {
						SketchwareUtil.toast("Please enter repository URL");
						enable.setChecked(false);
					}
					else {
						if (setRefSpecs.getText().toString().trim().equals("")) {
							SketchwareUtil.toast("Please enter RefSpecs/ Branch Reference");
							enable.setChecked(false);
						}
						else {
							if (username.getText().toString().trim().equals("")) {
								SketchwareUtil.toast("Please enter Github username");
								enable.setChecked(false);
							}
							else {
								if (pass.getText().toString().trim().equals("")) {
									SketchwareUtil.toast("Please enter Github access token");
									enable.setChecked(false);
								}
								else {
									map = new HashMap<>();
									map.put("repository", url.getText().toString());
									map.put("RefSpecs", setRefSpecs.getText().toString());
									map.put("username", username.getText().toString());
									map.put("token", pass.getText().toString());
									JsonMAP.add(map);
									FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.sketchware/data/1079/github_config"), new Gson().toJson(JsonMAP));
								}
							}
						}
					}
				}
				else {
					FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.sketchware/data/1079/github_config"), "[]");
				}
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("Github Manager");
                sc_id = getIntent().getStringExtra("sc_id");

		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir() +"/.sketchware/data/"+sc_id+"/github_config") && !FileUtil.readFile(FileUtil.getExternalStorageDir()+"/.sketchware/data/"+sc_id+"/github_config").equals("[]")) {
			JsonMAP = new Gson().fromJson(FileUtil.readFile(FileUtil.getExternalStorageDir()+"/.sketchware/data/"+sc_id+"/github_config"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			url.setText(JsonMAP.get((int)0).get("repository").toString());
			setRefSpecs.setText(JsonMAP.get((int)0).get("RefSpecs").toString());
			username.setText(JsonMAP.get((int)0).get("username").toString());
			pass.setText(JsonMAP.get((int)0).get("token").toString());
			enable.setChecked(true);
			push_data.setVisibility(View.VISIBLE);
			create_token.setVisibility(View.GONE);
		}
		else {
			enable.setChecked(false);
			push_data.setVisibility(View.GONE);
			create_token.setVisibility(View.VISIBLE);
			FileUtil.writeFile(FileUtil.getExternalStorageDir()+ "/.sketchware/data/"+sc_id+"/github_config", "[]");
		}
		_setBackground(create_token, 12, 2, "#E91E63", true);
		_setBackground(push_data, 12, 2, "#4caf50", true);
	}
	
	public void _setBackground(final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}

 	public void GiTPUSHAll(final String _filePATH, final String _setMessage, final String _UserName, final String _PassWord, final String _RemoteURL, final String _setRefSpecs) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		final Handler handler = new Handler(Looper.getMainLooper());
		
		 executor.execute(new Runnable() {
		    @Override
		   public void run() {
		     try(Git git = Git.open(new File(_filePATH))) {
		      //Add Files to git
	 	        git.add().addFilepattern(".").call();
		        //Set Commit -m
	 	        git.commit().setMessage(_setMessage).call();
	 	        //Push git 
	 	        PushCommand push = git.push();
                 push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(_UserName, _PassWord));
  		        push.setRemote(_RemoteURL);
 	 	        push.setRefSpecs(new RefSpec(_setRefSpecs));
 		        push.setForce(true);
 			push.call();
					          
		  	 Iterable<PushResult> results = push.call();
	   		for (PushResult r : results) {
   			  for(RemoteRefUpdate update : r.getRemoteUpdates()) {
	     	 	  System.out.println("Having result: " + update);
    		 	  if(update.getStatus() != RemoteRefUpdate.Status.OK && update.getStatus() != RemoteRefUpdate.Status.UP_TO_DATE) {
		  	        Result = "Push failed: "+ update.getStatus();
	 	             isSucces = false;
	    		     throw new RuntimeException(Result);
   	 		    }else{
    			      Result = "Successfully Pushed  & " + update.getStatus().toString();
	    		      isSucces = true;
		    		}
	   	 	 }
	   		}
					             
	    	 }catch(IOException | GitAPIException | JGitInternalException e) {
			    Result = e.getMessage();
			    isSucces = false;
			    e.printStackTrace();
			 }
				        
		  	handler.post(new Runnable() {
				 @Override
				  public void run() {
					  if(isSucces){
					     SketchwareUtil.toast(Result);
					  }else{
					     SketchwareUtil.toast(Result);
					  }
				  }
			   });
		   }
		});
		
	}
	
}

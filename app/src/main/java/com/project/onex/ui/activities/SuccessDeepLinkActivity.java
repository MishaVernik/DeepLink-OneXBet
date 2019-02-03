package com.project.onex.ui.activities;

import android.os.Bundle;
import com.project.onex.R;

import static com.project.onex.utils.Constants.LINK_SUCCESS;

public class SuccessDeepLinkActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onPostRequest(LINK_SUCCESS);
  }

  @Override
  public int layoutID() {
    return R.layout.activity_deep_link_success;
  }
}

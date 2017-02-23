package com.example.app.view.viewpresenter;

import com.example.app.model.Pill;
import java.util.List;

public interface ZinesMvpView extends LoadDataMvpView {

  void showPills(List<Pill> pills);
}

package com.coffe.shentao.mvp.presenter;


import com.coffe.shentao.mvp.model.IMainModel;
import com.coffe.shentao.mvp.model.MainModelImpl;
import com.coffe.shentao.mvp.model.MainModelNetImpl;
import com.coffe.shentao.mvp.view.IMainView;

public class MainPresenter {

//    虽然MVP的使用比较麻烦了些，但是它的有点也是很明显的，Model和View充分解耦，修改一个不会牵扯到另外一个。
//    另外，视图、业务逻辑也有可能会变，因此视图、业务逻辑抽取成接口，改变不同的实现类即可。 Presenter中只持有Model和VIew的引用，可以随时更换它们的实现类，从而实现对扩展是开放的。
//    因此MVP相对于MVC来说，规范比较明确，在系统架构上扩展性更加强。


   private IMainView mainView;
   private IMainModel mainModel;

   public MainPresenter(IMainView view){
       mainView=view;
       //mainModel=new MainModelImpl();
       mainModel=new MainModelNetImpl();  //如果对应不同的 同一个model 不同的实现  可以随时的切换
   }
    public MainPresenter(IMainView view,boolean isFromNet){
        mainView=view;
        mainModel=isFromNet?new MainModelNetImpl():new MainModelImpl();
        //mainModel=new MainModelImpl();
        //mainModel=new MainModelNetImpl();  //如果对应不同的 同一个model 不同的实现  可以随时的切换
    }

//    扩展：当然，如果你的项目经理脑抽了，改来改去，一时从网络加载，一时又觉得不好又从本地加载；
//    或者程序在不同的情况之下需要从不同的地方加载数据。
//    这里我们就可以使用策略模式，根据不同的情况来从不同的地方加载数据。
//    所以我们可以在Presenter中作如下修改：
//    需求2：我们的数据展示方法需要改变了，比如说要把数据展示到不同的控件上面等等，那么我们可以直接
//    新建一个不同的Activity，实现View的接口，作出相应的修改即可，这里不再赘述了。

   public void fetch(){
       mainView.showLoading();
       mainModel.loadData(new IMainModel.OnLoadCompleteListener() {
           @Override
           public void onComplete(String data) {
               mainView.showData(data);
           }
       });
   }

}

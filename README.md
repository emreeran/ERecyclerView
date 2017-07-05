ERecyclerView
=====

**ERecyclerView** is an implementation of Android RecyclerView with the following features:

* Add header and/or footer views

* Add quick return header and/or footer

* Add pull to refresh header and/or load more footer

* "Inject" additional views between items

* All combinations of options can be used simultaneously.

Usage
-----
**Include to your project**

If using Gradle add jcenter to repositories

        repositories {
            jcenter()
        }

Add to your module dependencies
        
        dependencies {
            compile 'com.emreeran.erecyclerview:erecyclerview:1.0.7'
        }

**Adding ERecyclerView to your layout**

Just put **ERecyclerView** to the position you wish in your layout
 
        <com.emreeran.erecyclerview.ERecyclerView
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
            
Set a ```LayoutManager``` and your implementation of ```RecyclerView.Adapter``` just like any ```RecyclerView```

Properties
--------------
**Refresh header**

Refresh header can be set by calling ```setPullToRefreshView``` which takes a resource id as parameter and an optional
```RefreshViewStateListener```. You can use the available ```TwitterRefreshHeader``` by passing ```TwitterRefreshHeader.getResource()``` and
 ```TwitterRefreshHeader.getListener()``` as parameters.
 
For refresh operations call ```setOnRefreshListener``` and give your implementation of the 
```OnRefreshListener``` as its parameter.
 
**Load more footer**

Load more footer can be set by calling ```setLoadMoreView``` which takes a resource id as parameter and an optional 
`LoadMoreViewStateListener`.  You can use the available ```DefaultLoadMoreFooter``` by passing `DefaultLoadMoreFooter.getResource()
` as parameter.

For load more operations call ```setOnLoadMoreListener```and give your implementation of the 
```OnLoadMoreListener``` as ites parameter.

**Quick return views**

Additional header and footer with quick return capability can be added to the layout. These views should be put on top of the 
**ERecyclerView** in the layout (See sample application for layout arrangement example). Use ```setQuickReturnHeader``` and 
```setQuickReturnFooter``` methods for setting the views as quick return. These methods takes the corresponding views and their 
respective heights as parameters. Additionally snap views option can be set by ```setQuickReturnViewsSnapable``` method. Snapping views
 on release, returns its previous state (showing || not showing) if half of its height is not past scrolling, and changes to its next 
 state if that threshold is past.
  
**Headers and footers**

Any number of header or footer views can be added using ```addHeaderView``` and ```addFooterView``` methods respectively.

**Injecting views**

Any number of views can be "injected" in available positions using the ```injectView``` method which takes the view to be injected and 
the target position as parameters. The new view will be inserted before the view that is in the target position.


**Acknowledgements**

Quick return functionality is taken from **Etienne Lawlor**'s QuickReturn project https://github.com/lawloretienne/QuickReturn

The general idea and stating point of this project was from **jianghejie**'s XRecyclerView https://github.com/jianghejie/XRecyclerView

Many thanks

**Credits**

Author Emre Eran (emre.eran@gmail.com - https://twitter.com/emreeran)

License
-------


        Copyright 2015 Emre Eran
        
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        
            http://www.apache.org/licenses/LICENSE-2.0
        
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.


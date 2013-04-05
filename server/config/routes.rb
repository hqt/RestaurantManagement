Server::Application.routes.draw do

  resources :dishes

  match '/testDishes/:id', :controller=>'dishes', :action=>'mymethod'
  match 'dishes/changes/free/:id', :controller=>'dishes', :action=>'change_free_status'

  resources :tables
  # modify status of ta table with :id, from free -> busy / busy -> dirty / dirty -> free (view code for clearer)
  match 'tables/changes/free/:id', :controller => 'tables', :action=>'change_free_status'
  match 'tables/changes/busy/:id', :controller => 'tables', :action=>'change_busy_status'
  match 'tables/changes/dirty/:id',:controller => 'tables', :action=>'change_dirty_status'
  # return all rows that in free / busy / dirty state
  match 'tables/filter/free', :controller=>'tables', :action=>'free'
  match 'tables/filter/busy', :controller=>'tables', :action=>'busy'
  match 'tables/filter/dirty', :controller=>'tables', :action=>'dirty'


  resources :orders
  # return all rows that in waiting / prepare state
  match 'orders/filter/waiting/', :controller=>'orders', :action=>'waiting'
  match 'orders/filter/prepare/', :controller=>'orders', :action=>'prepare'
  # modify status of order with :id, from waiting -> prepare or from prepare -> finish (view code for clearer)
  match 'orders/changes/waiting/:id', :controller=>'orders', :action=>'change_waiting_status'
  match 'orders/changes/prepare/:id', :controller=>'orders', :action=>'change_prepare_status'

  resources :tests


  resources :temps

  resources :temp_tables



  # The priority is based upon order of creation:
  # first created -> highest priority.

  # Sample of regular route:
  #   match 'products/:id' => 'catalog#view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   match 'products/:id/purchase' => 'catalog#purchase', :as => :purchase
  # This route can be invoked with purchase_url(:id => product.id)

  # Sample resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Sample resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Sample resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Sample resource route with more complex sub-resources
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', :on => :collection
  #     end
  #   end

  # Sample resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

  # You can have the root of your site routed with "root"
  # just remember to delete public/index.html.
  # root :to => 'welcome#index'

  # See how all your routes lay out with "rake routes"

  # This is a legacy wild controller route that's not recommended for RESTful applications.
  # Note: This route will make all actions in every controller accessible via GET requests.
  # match ':controller(/:action(/:id))(.:format)'
end

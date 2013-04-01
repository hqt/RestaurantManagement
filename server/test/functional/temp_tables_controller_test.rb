require 'test_helper'

class TempTablesControllerTest < ActionController::TestCase
  setup do
    @temp_table = temp_tables(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:temp_tables)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create temp_table" do
    assert_difference('TempTable.count') do
      post :create, temp_table: @temp_table.attributes
    end

    assert_redirected_to temp_table_path(assigns(:temp_table))
  end

  test "should show temp_table" do
    get :show, id: @temp_table
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @temp_table
    assert_response :success
  end

  test "should update temp_table" do
    put :update, id: @temp_table, temp_table: @temp_table.attributes
    assert_redirected_to temp_table_path(assigns(:temp_table))
  end

  test "should destroy temp_table" do
    assert_difference('TempTable.count', -1) do
      delete :destroy, id: @temp_table
    end

    assert_redirected_to temp_tables_path
  end
end

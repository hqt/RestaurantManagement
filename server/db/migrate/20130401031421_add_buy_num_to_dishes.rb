class AddBuyNumToDishes < ActiveRecord::Migration
  def change
    add_column :dishes, :BuyNum, :integer

  end
end

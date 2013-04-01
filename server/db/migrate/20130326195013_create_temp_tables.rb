class CreateTempTables < ActiveRecord::Migration
  def change
    create_table :temp_tables do |t|
      t.string :name
      t.string :id

      t.timestamps
    end
  end
end

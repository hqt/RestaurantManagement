class TempTablesController < ApplicationController
  # GET /temp_tables
  # GET /temp_tables.json
  def index
    @temp_tables = TempTable.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @temp_tables }
    end
  end

  # GET /temp_tables/1
  # GET /temp_tables/1.json
  def show
    @temp_table = TempTable.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @temp_table }
    end
  end

  # GET /temp_tables/new
  # GET /temp_tables/new.json
  def new
    @temp_table = TempTable.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @temp_table }
    end
  end

  # GET /temp_tables/1/edit
  def edit
    @temp_table = TempTable.find(params[:id])
  end

  # POST /temp_tables
  # POST /temp_tables.json
  def create
    @temp_table = TempTable.new(params[:temp_table])

    respond_to do |format|
      if @temp_table.save
        format.html { redirect_to @temp_table, notice: 'Temp table was successfully created.' }
        format.json { render json: @temp_table, status: :created, location: @temp_table }
      else
        format.html { render action: "new" }
        format.json { render json: @temp_table.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /temp_tables/1
  # PUT /temp_tables/1.json
  def update
    @temp_table = TempTable.find(params[:id])

    respond_to do |format|
      if @temp_table.update_attributes(params[:temp_table])
        format.html { redirect_to @temp_table, notice: 'Temp table was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @temp_table.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /temp_tables/1
  # DELETE /temp_tables/1.json
  def destroy
    @temp_table = TempTable.find(params[:id])
    @temp_table.destroy

    respond_to do |format|
      format.html { redirect_to temp_tables_url }
      format.json { head :no_content }
    end
  end
end

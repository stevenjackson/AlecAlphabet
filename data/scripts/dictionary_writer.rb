require 'json'
class DictionaryWriter

  def initialize(filename)
    @f = File.open(filename, 'w')
    @f.write "[\n"
  end

  def write hash
    @f.write ",\n" if @add_comma
    @add_comma = true 

    @f.write hash.to_json
  end

  def close
    @f.write "\n]"
    @f.close
  end
end

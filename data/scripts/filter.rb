require 'json'
out = File.open('filtered.json', 'w')
File.readlines('dictionary.json').each do |line|
  if line =~ /({.*})/
    word = JSON[$1]

    unless word['word'] =~ /\s+/
      out.write "#{word.to_json},\n"
    end
  else
    out.write line
  end
end

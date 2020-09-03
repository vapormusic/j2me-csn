package javazoom.jlme.decoder;


final class D17 {

public static final short[][] Huffman10={{8,1},{4,1},{2,1},{0,0},{0,16},{2,1},{0,1},{0,17},{10,1},{4,1},{2,1},{0,32},{0,33},{2,1},{0,18},{2,1},{0,2},{0,34},{12,1},{6,1},{4,1},{2,1},{0,48},{0,3},{0,49},{2,1},{0,19},{2,1},{0,50},{0,35},{12,1},{4,1},{2,1},{0,65},{0,20},{4,1},{2,1},{0,64},{0,51},{2,1},{0,66},{0,36},{10,1},{6,1},{4,1},{2,1},{0,4},{0,80},{0,67},{2,1},{0,52},{0,81},{8,1},{4,1},{2,1},{0,21},{0,82},{2,1},{0,37},{0,68},{6,1},{4,1},{2,1},{0,5},{0,84},{0,83},{2,1},{0,53},{2,1},{0,69},{0,85}};

public static final short[][] Huffman11 ={{2,1},{0,0},{4,1},{2,1},{0,16},{0,1},{10,1},{2,1},{0,17},{4,1},{2,1},{0,32},{0,2},{2,1},{0,33},{0,18},{28,1},{8,1},{4,1},{2,1},{0,34},{0,48},{2,1},{0,49},{0,19},{8,1},{4,1},{2,1},{0,3},{0,50},{2,1},{0,35},{0,64},{4,1},{2,1},{0,65},{0,20},{4,1},{2,1},{0,4},{0,51},{2,1},{0,66},{0,36},{28,1},{10,1},{6,1},{4,1},{2,1},{0,80},{0,5},{0,96},{2,1},{0,97},{0,22},{12,1},{6,1},{4,1},{2,1},{0,67},{0,52},{0,81},{2,1},{0,21},{2,1},{0,82},{0,37},{4,1},{2,1},{0,38},{0,54},{0,113},{20,1},{8,1},{2,1},{0,23},{4,1},{2,1},{0,68},{0,83},{0,6},{6,1},{4,1},{2,1},{0,53},{0,69},{0,98},{2,1},{0,112},{2,1},{0,7},{0,100},{14,1},{4,1},{2,1},{0,114},{0,39},{6,1},{2,1},{0,99},{2,1},{0,84},{0,85},{2,1},{0,70},{0,115},{8,1},{4,1},{2,1},{0,55},{0,101},{2,1},{0,86},{0,116},{6,1},{2,1},{0,71},{2,1},{0,102},{0,117},{4,1},{2,1},{0,87},{0,118},{2,1},{0,103},{0,119}};

public static final short[][] Huffman12 ={{6,1},{2,1},{0,0},{2,1},{0,16},{0,1},{8,1},{2,1},{0,17},{4,1},{2,1},{0,32},{0,2},{0,18},{24,1},{8,1},{2,1},{0,33},{2,1},{0,34},{2,1},{0,48},{0,3},{4,1},{2,1},{0,49},{0,19},{4,1},{2,1},{0,50},{0,35},{4,1},{2,1},{0,64},{0,4},{2,1},{0,65},{0,20},{30,1},{16,1},{10,1},{4,1},{2,1},{0,66},{0,36},{4,1},{2,1},{0,51},{0,67},{0,80},{4,1},{2,1},{0,52},{0,81},{0,97},{6,1},{2,1},{0,22},{2,1},{0,6},{0,38},{2,1},{0,98},{2,1},{0,21},{2,1},{0,5},{0,82},{16,1},{10,1},{6,1},{4,1},{2,1},{0,37},{0,68},{0,96},{2,1},{0,99},{0,54},{4,1},{2,1},{0,112},{0,23},{0,113},{16,1},{6,1},{4,1},{2,1},{0,7},{0,100},{0,114},{2,1},{0,39},{4,1},{2,1},{0,83},{0,53},{2,1},{0,84},{0,69},{10,1},{4,1},{2,1},{0,70},{0,115},{2,1},{0,55},{2,1},{0,101},{0,86},{10,1},{6,1},{4,1},{2,1},{0,85},{0,87},{0,116},{2,1},{0,71},{0,102},{4,1},{2,1},{0,117},{0,118},{2,1},{0,103},{0,119}};

public static final short[][] Huffman13 ={{12,1},{4,1},{2,1},{0,16},{0,1},{2,1},{0,17},{2,1},{0,0},{2,1},{0,32},{0,2},{16,1},{4,1},{2,1},{0,33},{0,18},{4,1},{2,1},{0,34},{0,49},{2,1},{0,19},{2,1},{0,48},{2,1},{0,3},{0,64},{26,1},{8,1},{4,1},{2,1},{0,50},{0,35},{2,1},{0,65},{0,51},{10,1},{4,1},{2,1},{0,20},{0,66},{2,1},{0,36},{2,1},{0,4},{0,80},{4,1},{2,1},{0,67},{0,52},{2,1},{0,81},{0,21},{28,1},{14,1},{8,1},{4,1},{2,1},{0,82},{0,37},{2,1},{0,83},{0,53},{4,1},{2,1},{0,96},{0,22},{0,97},{4,1},{2,1},{0,98},{0,38},{6,1},{4,1},{2,1},{0,5},{0,6},{0,68},{2,1},{0,84},{0,69},{18,1},{10,1},{4,1},{2,1},{0,99},{0,54},{4,1},{2,1},{0,112},{0,7},{0,113},{4,1},{2,1},{0,23},{0,100},{2,1},{0,70},{0,114},{10,1},{6,1},{2,1},{0,39},{2,1},{0,85},{0,115},{2,1},{0,55},{0,86},{8,1},{4,1},{2,1},{0,101},{0,116},{2,1},{0,71},{0,102},{4,1},{2,1},{0,117},{0,87},{2,1},{0,118},{2,1},{0,103},{0,119}};

public static final short[][] Huffman14 ={{2,1},{0,0},{6,1},{2,1},{0,16},{2,1},{0,1},{0,17},{28,1},{8,1},{4,1},{2,1},{0,32},{0,2},{2,1},{0,33},{0,18},{8,1},{4,1},{2,1},{0,34},{0,48},{2,1},{0,3},{0,49},{6,1},{2,1},{0,19},{2,1},{0,50},{0,35},{4,1},{2,1},{0,64},{0,4},{0,65},{70,1},{28,1},{14,1},{6,1},{2,1},{0,20},{2,1},{0,51},{0,66},{4,1},{2,1},{0,36},{0,80},{2,1},{0,67},{0,52},{4,1},{2,1},{0,81},{0,21},{4,1},{2,1},{0,5},{0,82},{2,1},{0,37},{2,1},{0,68},{0,83},{14,1},{8,1},{4,1},{2,1},{0,96},{0,6},{2,1},{0,97},{0,22},{4,1},{2,1},{0,128},{0,8},{0,129},{16,1},{8,1},{4,1},{2,1},{0,53},{0,98},{2,1},{0,38},{0,84},{4,1},{2,1},{0,69},{0,99},{2,1},{0,54},{0,112},{6,1},{4,1},{2,1},{0,7},{0,85},{0,113},{2,1},{0,23},{2,1},{0,39},{0,55},{72,1},{24,1},{12,1},{4,1},{2,1},{0,24},{0,130},{2,1},{0,40},{4,1},{2,1},{0,100},{0,70},{0,114},{8,1},{4,1},{2,1},{0,132},{0,72},{2,1},{0,144},{0,9},{2,1},{0,145},{0,25},{24,1},{14,1},{8,1},{4,1},{2,1},{0,115},{0,101},{2,1},{0,86},{0,116},{4,1},{2,1},{0,71},{0,102},{0,131},{6,1},{2,1},{0,56},{2,1},{0,117},{0,87},{2,1},{0,146},{0,41},{14,1},{8,1},{4,1},{2,1},{0,103},{0,133},{2,1},{0,88},{0,57},{2,1},{0,147},{2,1},{0,73},{0,134},{6,1},{2,1},{0,160},{2,1},{0,104},{0,10},{2,1},{0,161},{0,26},{68,1},{24,1},{12,1},{4,1},{2,1},{0,162},{0,42},{4,1},{2,1},{0,149},{0,89},{2,1},{0,163},{0,58},{8,1},{4,1},{2,1},{0,74},{0,150},{2,1},{0,176},{0,11},{2,1},{0,177},{0,27},{20,1},{8,1},{2,1},{0,178},{4,1},{2,1},{0,118},{0,119},{0,148},{6,1},{4,1},{2,1},{0,135},{0,120},{0,164},{4,1},{2,1},{0,105},{0,165},{0,43},{12,1},{6,1},{4,1},{2,1},{0,90},{0,136},{0,179},{2,1},{0,59},{2,1},{0,121},{0,166},{6,1},{4,1},{2,1},{0,106},{0,180},{0,192},{4,1},{2,1},{0,12},{0,152},{0,193},{60,1},{22,1},{10,1},{6,1},{2,1},{0,28},{2,1},{0,137},{0,181},{2,1},{0,91},{0,194},{4,1},{2,1},{0,44},{0,60},{4,1},{2,1},{0,182},{0,107},{2,1},{0,196},{0,76},{16,1},{8,1},{4,1},{2,1},{0,168},{0,138},{2,1},{0,208},{0,13},{2,1},{0,209},{2,1},{0,75},{2,1},{0,151},{0,167},{12,1},{6,1},{2,1},{0,195},{2,1},{0,122},{0,153},{4,1},{2,1},{0,197},{0,92},{0,183},{4,1},{2,1},{0,29},{0,210},{2,1},{0,45},{2,1},{0,123},{0,211},{52,1},{28,1},{12,1},{4,1},{2,1},{0,61},{0,198},{4,1},{2,1},{0,108},{0,169},{2,1},{0,154},{0,212},{8,1},{4,1},{2,1},{0,184},{0,139},{2,1},{0,77},{0,199},{4,1},{2,1},{0,124},{0,213},{2,1},{0,93},{0,224},{10,1},{4,1},{2,1},{0,225},{0,30},{4,1},{2,1},{0,14},{0,46},{0,226},{8,1},{4,1},{2,1},{0,227},{0,109},{2,1},{0,140},{0,228},{4,1},{2,1},{0,229},{0,186},{0,240},{38,1},{16,1},{4,1},{2,1},{0,241},{0,31},{6,1},{4,1},{2,1},{0,170},{0,155},{0,185},{2,1},{0,62},{2,1},{0,214},{0,200},{12,1},{6,1},{2,1},{0,78},{2,1},{0,215},{0,125},{2,1},{0,171},{2,1},{0,94},{0,201},{6,1},{2,1},{0,15},{2,1},{0,156},{0,110},{2,1},{0,242},{0,47},{32,1},{16,1},{6,1},{4,1},{2,1},{0,216},{0,141},{0,63},{6,1},{2,1},{0,243},{2,1},{0,230},{0,202},{2,1},{0,244},{0,79},{8,1},{4,1},{2,1},{0,187},{0,172},{2,1},{0,231},{0,245},{4,1},{2,1},{0,217},{0,157},{2,1},{0,95},{0,232},{30,1},{12,1},{6,1},{2,1},{0,111},{2,1},{0,246},{0,203},{4,1},{2,1},{0,188},{0,173},{0,218},{8,1},{2,1},{0,247},{4,1},{2,1},{0,126},{0,127},{0,142},{6,1},{4,1},{2,1},{0,158},{0,174},{0,204},{2,1},{0,248},{0,143},{18,1},{8,1},{4,1},{2,1},{0,219},{0,189},{2,1},{0,234},{0,249},{4,1},{2,1},{0,159},{0,235},{2,1},{0,190},{2,1},{0,205},{0,250},{14,1},{4,1},{2,1},{0,221},{0,236},{6,1},{4,1},{2,1},{0,233},{0,175},{0,220},{2,1},{0,206},{0,251},{8,1},{4,1},{2,1},{0,191},{0,222},{2,1},{0,207},{0,238},{4,1},{2,1},{0,223},{0,239},{2,1},{0,255},{2,1},{0,237},{2,1},{0,253},{2,1},{0,252},{0,254}};

public static final short[][] Huffman15 ={{0,0}};

public static final short[][] Huffman16 ={{16,1},{6,1},{2,1},{0,0},{2,1},{0,16},{0,1},{2,1},{0,17},{4,1},{2,1},{0,32},{0,2},{2,1},{0,33},{0,18},{50,1},{16,1},{6,1},{2,1},{0,34},{2,1},{0,48},{0,49},{6,1},{2,1},{0,19},{2,1},{0,3},{0,64},{2,1},{0,50},{0,35},{14,1},{6,1},{4,1},{2,1},{0,4},{0,20},{0,65},{4,1},{2,1},{0,51},{0,66},{2,1},{0,36},{0,67},{10,1},{6,1},{2,1},{0,52},{2,1},{0,80},{0,5},{2,1},{0,81},{0,21},{4,1},{2,1},{0,82},{0,37},{4,1},{2,1},{0,68},{0,83},{0,97},{90,1},{36,1},{18,1},{10,1},{6,1},{2,1},{0,53},{2,1},{0,96},{0,6},{2,1},{0,22},{0,98},{4,1},{2,1},{0,38},{0,84},{2,1},{0,69},{0,99},{10,1},{6,1},{2,1},{0,54},{2,1},{0,112},{0,7},{2,1},{0,113},{0,85},{4,1},{2,1},{0,23},{0,100},{2,1},{0,114},{0,39},{24,1},{16,1},{8,1},{4,1},{2,1},{0,70},{0,115},{2,1},{0,55},{0,101},{4,1},{2,1},{0,86},{0,128},{2,1},{0,8},{0,116},{4,1},{2,1},{0,129},{0,24},{2,1},{0,130},{0,40},{16,1},{8,1},{4,1},{2,1},{0,71},{0,102},{2,1},{0,131},{0,56},{4,1},{2,1},{0,117},{0,87},{2,1},{0,132},{0,72},{6,1},{4,1},{2,1},{0,144},{0,25},{0,145},{4,1},{2,1},{0,146},{0,118},{2,1},{0,103},{0,41},{92,1},{36,1},{18,1},{10,1},{4,1},{2,1},{0,133},{0,88},{4,1},{2,1},{0,9},{0,119},{0,147},{4,1},{2,1},{0,57},{0,148},{2,1},{0,73},{0,134},{10,1},{6,1},{2,1},{0,104},{2,1},{0,160},{0,10},{2,1},{0,161},{0,26},{4,1},{2,1},{0,162},{0,42},{2,1},{0,149},{0,89},{26,1},{14,1},{6,1},{2,1},{0,163},{2,1},{0,58},{0,135},{4,1},{2,1},{0,120},{0,164},{2,1},{0,74},{0,150},{6,1},{4,1},{2,1},{0,105},{0,176},{0,177},{4,1},{2,1},{0,27},{0,165},{0,178},{14,1},{8,1},{4,1},{2,1},{0,90},{0,43},{2,1},{0,136},{0,151},{2,1},{0,179},{2,1},{0,121},{0,59},{8,1},{4,1},{2,1},{0,106},{0,180},{2,1},{0,75},{0,193},{4,1},{2,1},{0,152},{0,137},{2,1},{0,28},{0,181},{80,1},{34,1},{16,1},{6,1},{4,1},{2,1},{0,91},{0,44},{0,194},{6,1},{4,1},{2,1},{0,11},{0,192},{0,166},{2,1},{0,167},{0,122},{10,1},{4,1},{2,1},{0,195},{0,60},{4,1},{2,1},{0,12},{0,153},{0,182},{4,1},{2,1},{0,107},{0,196},{2,1},{0,76},{0,168},{20,1},{10,1},{4,1},{2,1},{0,138},{0,197},{4,1},{2,1},{0,208},{0,92},{0,209},{4,1},{2,1},{0,183},{0,123},{2,1},{0,29},{2,1},{0,13},{0,45},{12,1},{4,1},{2,1},{0,210},{0,211},{4,1},{2,1},{0,61},{0,198},{2,1},{0,108},{0,169},{6,1},{4,1},{2,1},{0,154},{0,184},{0,212},{4,1},{2,1},{0,139},{0,77},{2,1},{0,199},{0,124},{68,1},{34,1},{18,1},{10,1},{4,1},{2,1},{0,213},{0,93},{4,1},{2,1},{0,224},{0,14},{0,225},{4,1},{2,1},{0,30},{0,226},{2,1},{0,170},{0,46},{8,1},{4,1},{2,1},{0,185},{0,155},{2,1},{0,227},{0,214},{4,1},{2,1},{0,109},{0,62},{2,1},{0,200},{0,140},{16,1},{8,1},{4,1},{2,1},{0,228},{0,78},{2,1},{0,215},{0,125},{4,1},{2,1},{0,229},{0,186},{2,1},{0,171},{0,94},{8,1},{4,1},{2,1},{0,201},{0,156},{2,1},{0,241},{0,31},{6,1},{4,1},{2,1},{0,240},{0,110},{0,242},{2,1},{0,47},{0,230},{38,1},{18,1},{8,1},{4,1},{2,1},{0,216},{0,243},{2,1},{0,63},{0,244},{6,1},{2,1},{0,79},{2,1},{0,141},{0,217},{2,1},{0,187},{0,202},{8,1},{4,1},{2,1},{0,172},{0,231},{2,1},{0,126},{0,245},{8,1},{4,1},{2,1},{0,157},{0,95},{2,1},{0,232},{0,142},{2,1},{0,246},{0,203},{34,1},{18,1},{10,1},{6,1},{4,1},{2,1},{0,15},{0,174},{0,111},{2,1},{0,188},{0,218},{4,1},{2,1},{0,173},{0,247},{2,1},{0,127},{0,233},{8,1},{4,1},{2,1},{0,158},{0,204},{2,1},{0,248},{0,143},{4,1},{2,1},{0,219},{0,189},{2,1},{0,234},{0,249},{16,1},{8,1},{4,1},{2,1},{0,159},{0,220},{2,1},{0,205},{0,235},{4,1},{2,1},{0,190},{0,250},{2,1},{0,175},{0,221},{14,1},{6,1},{4,1},{2,1},{0,236},{0,206},{0,251},{4,1},{2,1},{0,191},{0,237},{2,1},{0,222},{0,252},{6,1},{4,1},{2,1},{0,207},{0,253},{0,238},{4,1},{2,1},{0,223},{0,254},{2,1},{0,239},{0,255}};


}
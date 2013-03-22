package comunicacao;

import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;

public class ListMus {
	
	public static String buscaMus(String path, String artista, String album, String genero, String titulo, String ano) throws Exception {
		//System.out.print(artista+"\n"+album+"\n"+genero+"\n"+titulo+"\n"+ano+"\n");
		
		File diretorio = new File(path); //diretório onde se localiza as músicas
		String[] arquivos = diretorio.list(); //todos os arquivos do diretorio	
		boolean[] mark = new boolean[arquivos.length];
		int total = 0;
		MP3File aux;
		AbstractID3v2 tag;
		
		for(int i = 0; i < /*30*/arquivos.length; i++) {
			if (arquivos[i].lastIndexOf(".mp3") > -1 &&
					arquivos[i].lastIndexOf(".mp3") == arquivos[i].length() - 4) {
				try {
					aux = new MP3File(path+arquivos[i]);
					tag = aux.getID3v2Tag();
					//System.out.print(i+"\n"+tag.getLeadArtist()+"\n"+tag.getAlbumTitle()+"\n"+tag.getSongGenre()+
					//		"\n"+tag.getSongTitle()+"\n"+tag.getSongTitle()+"\n");
					if ((artista.equals("") 	|| tag.getLeadArtist().equalsIgnoreCase(artista)) && 
							(album.equals("") 	|| tag.getAlbumTitle().equalsIgnoreCase(album)) &&
							(genero.equals("") 	|| tag.getSongGenre().equalsIgnoreCase(genero)) && 
							(titulo.equals("") 	|| tag.getSongTitle().equalsIgnoreCase(titulo)) && 
							(ano.equals("") 	|| tag.getYearReleased().equalsIgnoreCase(ano))) {
						mark[i] = true;
						total++;
					} else mark[i] = false;
				} catch (TagException e) {
					e.printStackTrace();
					//System.out.print("Erro Tag: "+arquivos[i]+"\n");
				} catch (IOException e) {
					e.printStackTrace();
					//System.out.print("Erro IO: "+arquivos[i]+"\n");
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.print("Erro: "+arquivos[i]+"\n");
				}
			} else {
				mark[i] = false;
			}
		}
		
		String str = total+"\n";
		for (int i = 0; i < arquivos.length; i++) if (mark[i]) str += new File(path+arquivos[i]).length() + " " + arquivos[i] + "\n";
		//System.out.print(str);
		return str;
	}
}
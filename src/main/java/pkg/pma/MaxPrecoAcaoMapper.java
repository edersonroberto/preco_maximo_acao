package pkg.pma;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxPrecoAcaoMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {

		String linha = value.toString();
		// Ignora valores nulos
		if (linha != null) {
			String[] arrayLinha = linha.split(",");

			// Ignora o cabe�alho
			if (!linha.contains("Date")) {
				String ano = arrayLinha[0].substring(0, 4);
				
				//Se o valor da coluna 3 for nulo n�o realiza o parse e n�o salvo o valor para reduce
				if (!arrayLinha[2].equals("null")) {
					double maxima = Double.parseDouble(arrayLinha[2]);
					context.write(new Text(ano), new DoubleWritable(maxima));
				}
			}
		}
	}

}

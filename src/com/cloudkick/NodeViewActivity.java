package com.cloudkick;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NodeViewActivity extends Activity {
	private Node node;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle data = this.getIntent().getExtras();
		node = (Node) data.getSerializable("node");

		RelativeLayout nodeView;
		String inflater = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li = (LayoutInflater) getSystemService(inflater);

		nodeView = new RelativeLayout(this);
		li.inflate(R.layout.node_view, nodeView, true);

		// Set the a color representing the state
		TextView statusView = (TextView)nodeView.findViewById(R.id.node_detail_status);
		statusView.setBackgroundDrawable(new ColorDrawable(node.getStateColor()));

		// Set the background
		((RelativeLayout) nodeView.findViewById(R.id.node_detail_header))
				.setBackgroundDrawable(new ColorDrawable(node.color));

		// Fill in the views
		((TextView) nodeView.findViewById(R.id.node_detail_name)).setText(node.name);
		((TextView) nodeView.findViewById(R.id.node_detail_tags)).setText(node.getTagString());
		((TextView) nodeView.findViewById(R.id.value_ip_addr)).setText(node.ipAddress);
		((TextView) nodeView.findViewById(R.id.value_provider)).setText(node.providerName);
		((TextView) nodeView.findViewById(R.id.value_status)).setText(node.status);
		((TextView) nodeView.findViewById(R.id.value_agent)).setText(node.agentState);

		setContentView(nodeView);
		setTitle("Node: " + node.name);
	}
}